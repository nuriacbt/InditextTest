Proyecto realizado para la prueba técnica, enmarcado dentro del proceso de selección de AMARIS, para el cliente Inditex. 
Este consiste en la realización de un endpoint REST que permite recuperar los datos pertenecientes a un producto determinado de 
una de las cadenas del grupo inditex, en una fecha determinada.

Ejemplo de llamada al endpoint: 
	GET: /api/v1/brands/1/products/35455/prices?aplicationDate=2020-06-14T18:30:01.000-00:00
	
	Parámetros de la request: 
		- brandId (PATH PARAM): id de la cadena (en el caso del ejemplo 1).
		- productId (PATH PARAM): id del producto de la cadena (en el caso del ejemplo 35455).
		- aplicationDate (REQUEST_PARAM): fecha y hora en las que se tiene que aplicar la 
			tarifa en cuestion. 
	
	Ejemplo de response: 
	{
		"brandId": 1,
		"productId": 35455,
		"priceList": {
			"priceListId": 1,
			"description": "Tarifa num 1"
		},
		"startDate": "2020-06-14T00:00:00",
		"endDate": "2020-12-31T23:59:59",
		"price": 35.5,
		"currency": "EUR"
	}
	
	
El servicio devuelve responses de error personalizadas (con código 500), en caso de que la brandId o el productID sean incorrectos 
o no se encuentre ninguna tarifa a aplicar para la fecha indicada. 

Ejemplo de response errónea personalizada:

{
    "timestamp": "2021-09-21 08:06:50",
    "status": 500,
    "error": "No se ha encontrado la cadena con Id 6"
}

Otro aspecto a destacar es que se ha utilizado JAVA 11 para el desarrollo y compilación del proyecto, y también el patrón de arquitectura hexagonal 
para el desarrollo y la estructura de paquetes.

Principales librerías usadas:
- springBoot 2.5.4
- springData con JPA 3.5.4
- h2 database 1.4
- lombook 1.18
- mapstruct 1.4.2.Final
- JUNIT 4.13.2
- mockito 3.9.0
