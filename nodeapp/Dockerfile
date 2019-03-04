FROM node:10-alpine

RUN apk add --no-cache --virtual .gyp python make g++
# Create app directory
WORKDIR /home/Desktop/node/nodeapp


COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm install --only=production

# Bundle app source
COPY . .

#EXPOSE 8082
CMD [ "npm", "start" ]
