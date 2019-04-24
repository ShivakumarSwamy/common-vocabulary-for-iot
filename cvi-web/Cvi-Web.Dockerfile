FROM node:8.16.0-alpine as node
WORKDIR /app
COPY . /app
RUN npm install
RUN npm run build

FROM nginx:1.15.12-alpine
COPY --from=node /app/dist/cvi-web /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
