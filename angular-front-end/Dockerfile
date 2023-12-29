# STAGE 1: Compile and build angular codebase

# Use official node image as the base image
FROM node:latest as build

# Set the working directory
WORKDIR /usr/local/app

# Add the source code app
COPY ./ /usr/local/app/

#Install all the dependancies
RUN npm install

#Generate the build of the application
RUN npm run build

# STAGE 2: serve app with nginx server

# Use official nginx image as the base image
FROM nginx:latest

# Copy the build output to replace the default nginx contents
COPY --from=build /usr/local/app/dist/angular-front-end/browser /usr/share/nginx/html

#Expose port 80
#EXPOSE 80

