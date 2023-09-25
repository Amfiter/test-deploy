# Используем официальный образ OpenJDK 17 как базовый образ
FROM adoptopenjdk/openjdk17:alpine-slim AS build

# Устанавливаем переменную окружения для Gradle
ENV GRADLE_USER_HOME=/gradle

# Устанавливаем Gradle
RUN apk update && apk add --no-cache bash curl && \
    curl -s "https://get.sdkman.io" | bash && \
    source "$HOME/.sdkman/bin/sdkman-init.sh" && \
    sdk install gradle && \
    apk del curl

# Создаем и переключаемся в рабочую директорию
WORKDIR /app

# Копируем файлы с зависимостями Gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .

# Копируем исходный код
COPY src src

# Собираем приложение с помощью Gradle
RUN gradle build --no-daemon

# Фаза 2: Создаем минимальный образ для выполнения приложения
FROM adoptopenjdk/openjdk17:alpine-jre

# Переключаемся в рабочую директорию
WORKDIR /app

# Копируем собранный JAR-файл из предыдущей фазы сборки
COPY --from=build /app/build/libs/test-deploy.jar .

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "test-deploy.jar"]