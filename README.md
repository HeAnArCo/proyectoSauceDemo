Para esta prueba se requiere adaptar el ambiente Java, Maven, IntelliJ para ello se requiere lo siguiente:

**Java JDK**

Para Windows:
1. Visita el sitio oficial de Oracle JDK
2. Descarga la versión más reciente de JDK (recomendado JDK 11 o superior)
3. Ejecuta el instalador y sigue las instrucciones
4. Configura las variables de entorno:
   * JAVA_HOME: Ruta de instalación del JDK (ej: C:\Program Files\Java\jdk-11.0.15)
   * %JAVA_HOME%\bin al PATH del sistema
  
Una vez se realice esto, verificar el java -version para verificar que se encuentra instalada.

**Maven**

Para Windows:
1. Descarga Maven desde https://maven.apache.org/download.cgi
2. Extrae el archivo ZIP en una ubicación permanente (ej: C:\Program Files\Apache\Maven)
3. Configura las variables de entorno:
   * MAVEN_HOME: Ruta de extracción de Maven
   * %MAVEN_HOME%\bin al PATH del sistema

Una vez se realice esto, verificar el mvn -version para verificar que se encuentra instalada.

**Intellij**

Se debe descargar IntelliJ IDEA Community Edition desde https://www.jetbrains.com/idea/download/

1. Ejecuta el instalador y sigue las instrucciones
2. Selecciona las opciones recomendadas durante la instalación
3. Al abrir IntelliJ por primera vez:
4. Configura el JDK previamente instalado
5. Instala los plugins recomendados para Java development

**ChromeDriver**

1. Verificar la versión de Chrome instalada (Menú → Ayuda → Acerca de Google Chrome)
2. Descarga ChromeDriver correspondiente a tu versión desde https://chromedriver.chromium.org/downloads
3. Extrae el archivo y coloca chromedriver.exe en una carpeta de tu preferencia
4. Agrega la ruta de ChromeDriver al PATH del sistema o configura la ruta directamente en tu proyecto de pruebas

