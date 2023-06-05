# PowerPickupPlanet

Todo el código esta comentado, en el README solamente se explica de forma general para qué sirve cada archivo y su función que desempeña

# FRONT-END
# PPPFront

Este proyecto fue generado con [Angular CLI](https://github.com/angular/angular-cli) version 15.2.1.

## Contenido

La distribucion del contenido se divide en dos partes principalmente. La parte de la vista y los servicos.
* index.html
  * Vista única en donde se producen todos los procesos de Angular
* app -- directorio
  * shared -- directorio
    * interface -- directorio
      * producto -- directorio
        * completeProducto-- interfaz
          * Vista de un producto
        * listProducto -- interfaz
          * Listado de los producto de forma general
        * marca -- interfaz
          * Atributo de la entidad producto
        * modelo -- interfaz
          * Atributo de la entidad producto
        * newProducto -- interfaz
          * Formulario para un producto
      * usuario -- directorio
        * login-usuario -- interfaz
          * Registro de un usuario a la aplicacion web
        * new-usuario
      * calculadora -- interfaz
        * Formulario para la realizacion del calculo
      * instalacion -- interfaz
        * Formulario y Listado de las instalaciones
      * mantenimiento -- interfaz
        * Formulario y Listado de los mantenimientos
    * service
      * calculadora
        * Conecta con el backend y realiza los calculos
      * instalacion
        * Conecta con el backend y realiza el CRUD de instalacion
      * mantenimiento
        * Conecta con el backend y realiza el CRUD de mantenimiento
      * producto
        * Conecta con el backend y realiza el CRUD de producto
      * seguridad
        * AuthGuard
          * Se encarga de la seguridad de parte del cliente
            * Guard
            * Service del guard
        * User
          * Conecta con el backend y realiza el login del usuario
  * views -- directorio
    * welcome -- componente
      * Pagina de inicio
    * calculadora -- componente
      * Vista y procesos de calculo para el ahorro de dinero y energia producido por un panel solar
    * catalogo
      * catalogo -- componente
        * Muestra todos los productos
      * edit-producto -- componente
        * Editado de un mantenimiento por su id
      * new-producto -- componente
        * Crea un nuevo producto
    * mantenimiento
      * mantenimiento -- componente
        * Muestra todos los mantenimientos
      * edit-mantenimiento -- componente
        * Editado de un mantenimiento por su id
      * new-mantenimiento -- componente
        * Crea un nuevo mantenimiento
    * instalacion
      * instalacion -- componente
        * Muestra todas las instalaciones
      * edit-instalacion -- componente
        * Editado de un mantenimiento por su id
      * new-instalacion -- componente
        * Crea una nueva instalacion
    * login -- componente
      * Se realiza el registro de un usuario existente
    * error -- componente
      * En caso de acceder a una direccion erronea se gestiona el error
* assets
Se guardan las imagenes que van a ser usadas en toda la aplicacion a excepcion del favicon

### Descargas de extension para Angular
* Extension para el uso de bootstrap 5.2 y animaciones de este mismo
  * "@popperjs/core": "^2.11.7"
  * "bootstrap": "^5.2.3"
  * "jquery": "^3.6.4"
* Manejo de las cookies
  * "ngx-cookie-service": "^15.0.0"
* Uso de Google Maps
  * "@types/google.maps": "^3.53.2"
* Manejo de errores generales o especificos
    "rxjs": "~7.8.0"

Para mas informacion ver [package.json](./package.json)

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

# BACK-END
# PowerPickupPlanet

## Properties
### Puerto de uso
## `server.port=9000`

Conexion con la base de datos SQL
`spring.datasource.url=jdbc:mysql://localhost:3306/ppp`

Usuario de la base de datos
`spring.datasource.username=root`

Password de usuario
`spring.datasource.password=ADMINadmin2003@`

Driver de la base de datos
`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`

Nos permite ver el JPA
`spring.jpa.open-in-view=true`

Nos permite ver los procesos SQL
`spring.jpa.show-sql=true`

Cuando se inicia la apliacion se destruye y crea la base de datos
`spring.jpa.hibernate.ddl-auto=create-drop`

## Carpetas implicadas
* conifg
  * InitialDataConfiguration
    * Inicializa los datos para la base de datos
* controlador
  * CalculoController
    * calcularAhorroEnergiaYDinero()
      * Realiza el calculo para el ahorro de energia y dinero
  * InstalacionController
    * listarInstalaciones()
    * crearInstalacion()
    * obtenerInstalacion()
    * actualizarInstalacion()
    * eliminarInstalacion()
  * MantenimientoController
    * listarMantenimientos()
    * crearMantenimiento()
    * obtenerMantenimiento()
    * actualizarMantenimiento()
    * eliminarInstalacion()
  * ProductoController
    * crearProducto()
    * obtenerProductoPorId()
    * obtenerTodosLosProductos()
    * actualizarProducto()
    * eliminarProductoPorId()
  * UsuarioController
    * findAll()
    * findById()
    * login()
    * save()
    * update()
    * deleteById()
* entidad
  * Entidades usadas para la aplicación
    * Calculo
    * Instalacion
    * Mantenimiento
    * Marca
    * Modelo
    * Producto
    * TipoMantenimiento
    * Usuario
* interfaz
  * CalculoRepository
  * InstalacionRepository
  * MantenimientoRepository
  * ProductoRepository
  * UsuarioRepository
* seguridad
  * WebConfig
    * addCorsMappings()
* servicio
  * CalculoService
  * InstalacionService
  * MantenimientoService
  * ProductoService
  * UsuarioService
