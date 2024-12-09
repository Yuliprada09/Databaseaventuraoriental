---


---

<h1 id="proyecto-crud-de-usuarios-con-jdbc-y-mysql">Proyecto CRUD de usuarios con JDBC y MySQL</h1>
<h1 id="sobre-el-proyecto">Sobre el proyecto</h1>
<p>El repositorio creado es un CRUD, esto quiere decir crear, leer, analizar y eliminar usuarios desarrollado en Java utilizando JDBC y MySQL.</p>
<h2 id="tecnología-jbdc">Tecnología JBDC</h2>
<p>Se ha utilizado para este proyecto (Java Database Connectivity) como tecnología base para realizar la conexión con la base de datos de MySQL</p>
<p><strong>Ventajas de JDBC</strong></p>
<ul>
<li>Portabilidad: las aplicaciones desarrolladas con JDBC son independientes de la base de datos que se utilice, siempre que el driver JDBC correspondiente esté disponible. Esto permite cambiar la base de datos subyacente sin modificar el código.</li>
<li>Conexión a múltiples bases de datos: JDBC es compatible con una variedad de bases de datos, como MySQL, PostgreSQL, Oracle, SQL Server, SQLite, entre otras.</li>
<li>Facilidad de integración: JDBC se integra fácilmente con tecnologías Java, como aplicaciones de escritorio, aplicaciones web, servicios, spring framework, entre otras.</li>
<li>Interoperabilidad: Con JDBC puedes interactuar con bases de datos utilizando SQL directamente desde Java, lo que facilita el uso de comandos SQL para realizar operaciones de consulta, inserción, actualización y eliminación de datos.</li>
</ul>
<p><strong>Desventajas de JDBC</strong></p>
<ul>
<li>Código más complejo para operaciones avanzadas<br>
Poca abstracción: JDBC es una API de bajo nivel que requiere una cantidad considerable de código para realizar operaciones básicas y avanzadas. las operaciones como consultas, actualizaciones y manejo de conexiones requieren que el desarrollador escriba SQL directamente y controle manualmente la lógica de acceso.<br>
Sin patrones avanzados por defecto: No incluye patrones como DAO (Data Acces Object) o capas de abstracción que simplifiquen el acceso a los datos. Esto puede hacer que el código sea más difícil de mantener.</li>
<li>Escalabilidad limitada: Aunque JDBC es adecuado para la mayoría de las aplicaciones, en aplicaciones con un alto número de usuarios concurrentes o accesos complejos a bases de datos, puede resultar menos eficiente comparado con soluciones más avanzadas o frameworks ORM optimizados.</li>
</ul>
<h2 id="contenido-del-repositorio">Contenido del repositorio</h2>
<p>En este repositorio encontraras los siguientes archivos:</p>
<ul>
<li><code>Main.java</code>: Archivo principal que contiene la clase principal (<code>Main</code>).</li>
<li><code>DatabaseConnector.java</code>: Clase que gestiona la conexión y desconexión con la base de datos.</li>
<li><code>UsuarioManager.java</code>: Clase que contiene la lógica para realizar operaciones CRUD en la base de datos.</li>
</ul>
<h3 id="ide-y-herramientas-utilizadas">IDE y Herramientas Utilizadas</h3>
<p>El proyecto fue desarrollado utilizando IntelliJ IDEA. Las herramientas incluyen:</p>
<ul>
<li>Java versión “15.0.2”</li>
<li>MySQL Database</li>
<li>XAMPP (para el servidor MySQL)</li>
<li>IntelliJ IDEA</li>
</ul>

