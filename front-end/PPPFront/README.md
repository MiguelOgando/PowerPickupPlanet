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
        * [completeProducto](./src/app/shared/interface/producto/completeProducto.ts) -- interfaz
          * Vista de un producto
        * [listProducto](./src/app/shared/interface/producto/listProducto.ts) -- interfaz
          * Listado de los producto de forma general
        * [marca](./src/app/shared/interface/producto/marca.ts) -- interfaz
          * Atributo de la entidad producto
        * [modelo](./src/app/shared/interface/producto/modelo.ts) -- interfaz
          * Atributo de la entidad producto
        * [newProducto](./src/app/shared/interface/producto/newProducto.ts) -- interfaz
          * Formulario para un producto
      * usuario -- directorio
        * [login-usuario](./src/app/shared/interface/usuario/login-usuario.ts) -- interfaz
          * Registro de un usuario a la aplicacion web
        * new-usuario
      * [calculadora](./src/app/shared/interface/calculadora.ts) -- interfaz
        * Formulario para la realizacion del calculo
      * [instalacion](./src/app/shared/interface/instalacion.ts) -- interfaz
        * Formulario y Listado de las instalaciones
      * [mantenimiento](./src/app/shared/interface/mantenimiento.ts) -- interfaz
        * Formulario y Listado de los mantenimientos
    * service
      * [calculadora](./src/app/shared/service/calculadora/calculadora.service.ts)
        * Conecta con el backend y realiza los calculos
      * [instalacion](./src/app/shared/service/instalacion/instalacion.service.ts)
        * Conecta con el backend y realiza el CRUD de instalacion
      * [mantenimiento](./src/app/shared/service/mantenimiento/mantenimiento.service.ts)
        * Conecta con el backend y realiza el CRUD de mantenimiento
      * [producto](./src/app/shared/service/producto/producto.service.ts)
        * Conecta con el backend y realiza el CRUD de producto
      * seguridad
        * AuthGuard
          * Se encarga de la seguridad de parte del cliente
            * [Guard](./src/app/shared/service/seguridad/auth.guard.ts)
            * [Service del guard](./src/app/shared/service/seguridad/auth.service.ts)
        * [User](./src/app/shared/service/seguridad/user.service.ts)
          * Conecta con el backend y realiza el login del usuario
  * views -- directorio
    * [welcome](./src/app/views/welcome/) -- componente
      * Pagina de inicio
    * [calculadora](./src/app/views/calculadora/) -- componente
      * Vista y procesos de calculo para el ahorro de dinero y energia producido por un panel solar
    * catalogo
      * [catalogo](./src/app/views/catalogo/) -- componente
        * Muestra todos los productos
      * [edit-producto](./src/app/views/edit-producto/) -- componente
        * Editado de un mantenimiento por su id
      * [new-producto](./src/app/views/new-producto/) -- componente
        * Crea un nuevo producto
    * mantenimiento
      * [mantenimiento](./src/app/views/mantenimiento/) -- componente
        * Muestra todos los mantenimientos
      * [edit-mantenimiento](./src/app/views/edit-mantenimiento/) -- componente
        * Editado de un mantenimiento por su id
      * [new-mantenimiento](./src/app/views/new-mantenimiento/) -- componente
        * Crea un nuevo mantenimiento
    * instalacion
      * [instalacion](./src/app/views/instalacion/) -- componente
        * Muestra todas las instalaciones
      * [edit-instalacion](./src/app/views/edit-instalacion/) -- componente
        * Editado de un mantenimiento por su id
      * [new-instalacion](./src/app/views/new-instalacion/) -- componente
        * Crea una nueva instalacion
    * [login](./src/app/views/login/) -- componente
      * Se realiza el registro de un usuario existente
    * [error](./src/app/views/error/) -- componente
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
