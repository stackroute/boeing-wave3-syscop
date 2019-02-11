FROM node:10-alpine

# Create app directory
WORKDIR /home/user/Desktop/Avinash/SpringBoot/muzixappNode


COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm install --only=production

# Bundle app source
COPY . .

#EXPOSE 8082
CMD [ "npm", "start" ]
