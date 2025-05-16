# Todo App with Spring Boot and Tailwind CSS

This is a simple Todo application built with Spring Boot and styled with Tailwind CSS.

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) 17 or later
- Maven (optional, as the project includes Maven Wrapper)

## Setting Up JAVA_HOME

The application requires the JAVA_HOME environment variable to be set correctly.

### Windows

1. Find your JDK installation path (e.g., `C:\Program Files\Java\jdk-17`)
2. Set the JAVA_HOME environment variable:
   - Right-click on 'This PC' or 'My Computer' and select 'Properties'
   - Click on 'Advanced system settings'
   - Click on 'Environment Variables'
   - Under 'System variables', click 'New'
   - Variable name: `JAVA_HOME`
   - Variable value: Your JDK path (e.g., `C:\Program Files\Java\jdk-17`)
   - Click 'OK' to save
3. Add Java to your PATH:
   - Edit the 'Path' variable in system variables
   - Add `%JAVA_HOME%\bin` to the list
   - Click 'OK' to save

### macOS/Linux

Add these lines to your `~/.bash_profile` or `~/.zshrc`:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH
```

Then run `source ~/.bash_profile` or `source ~/.zshrc` to apply the changes.

## Running the Application

1. Open a terminal/command prompt
2. Navigate to the project directory
3. Run the application using Maven Wrapper:

```bash
# Windows
.\mvnw spring-boot:run

# macOS/Linux
./mvnw spring-boot:run
```

## Accessing the Application

Once the application is running:

1. Open your web browser
2. Navigate to [http://localhost:8080](http://localhost:8080)
3. You should see the Todo application with sample todo items

## Features

- View a list of todo items
- Sample data is automatically loaded on first run
- Responsive design with Tailwind CSS

## Database Access

The application uses an H2 in-memory database. You can access the H2 console:

1. Go to [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
2. JDBC URL: `jdbc:h2:mem:todo_db`
3. Username: `admin`
4. Password: `password`

## Development

For development with hot-reload of Tailwind CSS:

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

4. In another terminal, run the Spring Boot application with the Maven wrapper.

## Project Structure

- `src/main/java/com/example/todo_app/` - Java source files
  - `controllers/` - Web controllers
  - `models/` - Data models
  - `repositories/` - Data access
  - `services/` - Business logic
  - `config/` - Configuration classes
- `src/main/resources/` - Application resources
  - `templates/` - Thymeleaf templates
  - `static/` - Static resources
- `src/main/frontend/` - Frontend source files for Tailwind CSS