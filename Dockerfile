# Utilisez une image de base Alpine Linux avec curl
FROM alpine

# Installez curl
RUN apk --no-cache add curl

# Exposez un port
EXPOSE 8080

# Créez un répertoire pour votre application
RUN mkdir /app

# Utilisez curl pour télécharger le fichier JAR depuis Nexus
RUN curl -o /app/kaddem.jar http://192.168.1.101:8081/tn/esprit/spring/kaddem/1.0/kaddem-1.0.jar

# Commande d'exécution
CMD ["java", "-jar", "/app/kaddem.jar"]
