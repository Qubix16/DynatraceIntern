FROM node:16.13.0 as build-stage

WORKDIR /react-app

COPY package*.json ./

RUN npm install --legacy-peer-deps

COPY . .

EXPOSE 3000

CMD ["npm","start"]