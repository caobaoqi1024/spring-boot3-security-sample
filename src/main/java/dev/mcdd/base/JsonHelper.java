package dev.mcdd.base;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class JsonHelper {

	public static final ObjectMapper MAPPER = new ObjectMapper();
	public static final String DATE_FORMATTER_PATTERN = "YYYY-MM-dd HH:mm:ss";
	public static final String LOCAL_TIME_FORMATTER_PATTERN = "HH:mm:ss";
	public static final String LOCAL_DATE_FORMATTER_PATTERN = "YYYY-MM-dd";
	public static final String LOCAL_DATE_TIME_FORMATTER_PATTERN = "YYYY-MM-dd HH:mm:ss";

	static {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalTime.class, new
			LocalTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_TIME_FORMATTER_PATTERN)));
		javaTimeModule.addSerializer(LocalDate.class, new
			LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)));
		javaTimeModule.addSerializer(LocalDateTime.class, new
			LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMATTER_PATTERN)));
		MAPPER.registerModule(new ParameterNamesModule())
			.registerModule(new Jdk8Module())
			.registerModule(javaTimeModule);
		MAPPER.setDateFormat(new SimpleDateFormat(DATE_FORMATTER_PATTERN));
	}

	public static String toJSON(Object object) {
		Objects.requireNonNull(object);
		try {
			return MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T toObject(String JSON, Class<T> clazz) {
		Objects.requireNonNull(JSON);
		Objects.requireNonNull(clazz);
		try {
			return MAPPER.readValue(JSON, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}


}
