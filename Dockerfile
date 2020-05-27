FROM openjdk:8-jdk
COPY out/artifacts/MedAware_jar/*.jar /app/service.jar
ENV input="data/targets.csv"
ENV output="output/targets_groups.txt"
ENTRYPOINT java -jar /app/service.jar -i "$input" -o "$output"