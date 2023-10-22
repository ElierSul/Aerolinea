# AEROEXPRESSLATAM
Integrantes

Elier Sulbara, Valentina Valencia, David Rodriguez

A continuación se agrega la descripcion y el detalle del sistema de reserva de vuelos, el cual constituye el proyecto integrador entregado por la Organización Makaia

# Sistema de Reserva de Vuelos
El Sistema de Reserva de Vuelos o tambien llamado Aerolinea AeroExpres LATAM, es una solución informática diseñada para facilitar la gestión y reserva de vuelos para viajeros y compañías aéreas. Esta plataforma basada en la web brinda a los usuarios la posibilidad de buscar, visualizar y reservar vuelos

Contenido
1. Requisitos
2. Características
3. Documentacion
4. Uso

# Requisitos

Para la programacion del Sistema de Reserva de Vuelos, asegúrate de tener instalados los siguientes requisitos:

* Java 8 o superior
* Base de datos (MySQL)
* IDE Java IntelliJ opcional


# Caracteristicas 

El sistema de reserva de poder realizar las siguientes actividades 

1. Gestionar vuelos
2. Buscar vuelos por criterios de búsqueda
3. Reservar un vuelo
4. Permitir búsqueda por escalas en los vuelos


   


1.1 Gestion de vuelos 

Para la gestion de los vuelos se utilizo endpoint. Un endpoint en el contexto de desarrollo de aplicaciones se refiere a una URL específica a la que se puede acceder en un servicio web o API. Los endpoints son puntos de acceso que permiten a los clientes de la API (aplicaciones, dispositivos, usuarios, etc.) interactuar con el sistema y realizar operaciones específicas.

Para la creacion de los vuelos se uso un endpoint, el cual permite crear o registrar los vuelos

![img.png](img.png)

En la gestion de vuelo, los desarrolladores implementaron nuevos criterios en el cual al usurio se le permite mostrar, eliminar,editar y deasociar la informacion de los vuelos 

Endpoint de mostras vuelos 

![img_10.png](img_10.png)

Endpoint de eliminar vuelos 

![img_2.png](img_2.png)

Endpoint de editar vuelos 

![img_3.png](img_3.png)

Endpoint para asociar un vuelo con una escala

![img_4.png](img_4.png)

Endpoint para desasociar un vuelo de una escala

![img_5.png](img_5.png)

Endpoint para asociar un vuelo y un boleto

![img_6.png](img_6.png)

Endpoint para desasociar un vuelo de un boleto

![img_7.png](img_7.png)

2.1 Buscar vuelos por criterios de busquedad

En este endpoint se hace las busqueda de criterio atravez de su id

![img_8.png](img_8.png)

3.1 Reservar un vuelo 
este endpoint, le permite al usuario por hacer una reserva de un vuelo 

![img_9.png](img_9.png)


# Documentacion 

Modelado de diagrama de clases y entidad relacion, la creacion de estos modelados se diseñan con el objetivo de tener una visibilidad estructural del proyecto, para asi tener un desarrollo eficiente

MODELO ENTIDAD RELACION

![Entidad Relacion.jpeg](Entidad%20Relacion.jpeg)

DIAGRAMA DE CLASES

![Diagrama Clases.png](Diagrama%20Clases.png)



# Uso 


* Examina los vuelos disponibles y selecciona uno que se ajuste a tus necesidades.
* Realiza una reserva y completa el proceso de pago si es necesario.
* Los desarrolladores puede hacer uso de este proyecto, como punto de inicio para nuevos proyectos.


[Sistema de reservas de vuelos.pdf](https://github.com/ElierSul/Aerolinea/files/12842459/Sistema.de.reservas.de.vuelos.pdf)
