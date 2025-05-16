# Tailwind CSS Integration in Spring Boot

This document explains how Tailwind CSS has been integrated into this Spring Boot application.

## Overview

Instead of using Tailwind CSS via CDN, we've set up a proper build pipeline using Node.js and npm to compile Tailwind CSS. This approach offers several advantages:

1. **Better performance**: Only the CSS classes that are actually used in your application are included in the final CSS file.
2. **Offline development**: No need for an internet connection to load Tailwind CSS.
3. **Version control**: The exact version of Tailwind CSS is locked in your package.json.
4. **Custom configuration**: Easy to customize Tailwind CSS through the tailwind.config.js file.

## Project Structure

```
src/
├── main/
│   ├── frontend/               # Frontend source files
│   │   ├── src/
│   │   │   └── main.css        # Main CSS file with Tailwind directives
│   │   ├── package.json        # npm dependencies and scripts
│   │   └── tailwind.config.js  # Tailwind CSS configuration
│   ├── resources/
│   │   ├── static/
│   │   │   └── css/
│   │   │       └── tailwind.css # Compiled CSS (generated)
│   │   └── templates/
│   │       └── index.html      # HTML template using Tailwind CSS
```

## Dependencies Added

The following dependencies and plugins have been added to the `pom.xml`:

1. **Node.js and npm versions**:
   ```xml
   <properties>
       <node.version>v18.16.0</node.version>
       <npm.version>9.5.1</npm.version>
   </properties>
   ```

2. **WebJars Locator**:
   ```xml
   <dependency>
       <groupId>org.webjars</groupId>
       <artifactId>webjars-locator-core</artifactId>
       <version>0.52</version>
   </dependency>
   ```

3. **Frontend Maven Plugin**:
   ```xml
   <plugin>
       <groupId>com.github.eirslett</groupId>
       <artifactId>frontend-maven-plugin</artifactId>
       <version>1.12.1</version>
       <!-- Configuration omitted for brevity -->
   </plugin>
   ```

## How It Works

1. When you build the application with Maven, the frontend-maven-plugin will:
   - Install Node.js and npm
   - Install the npm dependencies defined in package.json
   - Run the build script to compile Tailwind CSS

2. The build script processes the main.css file with Tailwind directives and outputs the compiled CSS to src/main/resources/static/css/tailwind.css.

3. Spring Boot serves the compiled CSS file as a static resource.

4. The HTML templates reference the compiled CSS file with:
   ```html
   <link rel="stylesheet" href="/css/tailwind.css">
   ```

## Building and Running

To build the application with Tailwind CSS:

```bash
mvn clean install
```

To run the application:

```bash
mvn spring-boot:run
```

## Development Workflow

For development, you can use the watch mode to automatically recompile Tailwind CSS when files change:

1. Navigate to the frontend directory:
   ```bash
   cd src/main/frontend
   ```

2. Install npm dependencies (first time only):
   ```bash
   npm install
   ```

3. Start the watch mode:
   ```bash
   npm run watch
   ```

4. In another terminal, run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

## Customizing Tailwind CSS

To customize Tailwind CSS, edit the `tailwind.config.js` file. See the [Tailwind CSS documentation](https://tailwindcss.com/docs/configuration) for more details.