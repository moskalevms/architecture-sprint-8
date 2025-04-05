# Сборка приложения
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

COPY gradle ./gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./

RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

RUN useradd -m appuser && chown -R appuser:appuser /app
USER appuser

COPY --from=builder --chown=appuser:appuser /app/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -Dfile.encoding=UTF-8"

EXPOSE 8070

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]