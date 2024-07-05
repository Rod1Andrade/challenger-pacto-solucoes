# Etapa 1: Construir a aplicação Angular
FROM node:alpine

WORKDIR /app

COPY package*.json ./

RUN npm install -g @angular/cli

COPY . .

RUN npm install
EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0"]