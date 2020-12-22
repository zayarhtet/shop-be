# shop-be
Database for Shop Management System

### Postman Collection and API Endpoint
**Postman collection can be found in `src/main/resources/Shop.postman_collection.json`**
<br/>
The database schema can be found in `src/main/resources/shop-schema.sql`
<br/>
**API ENDPOINT - `http://localhost:8080`**

<br/>
MySQL Database is used as a standard database for this project.
<br/>

<br/>

**Before exporting the schema, please execute this query first**
`create schema shop;`

### Export database to `.sql` script file
*`-- export schema only`* <br/>
`mysqldump -u root -p --no-data dbname > dbname-schema.sql`
<br/><br/>
*`-- export data only`* <br/>
`mysqldump -u root -p --no-create-info dbname > dbname-data.sql`
<br/><br/>
*`-- export both schema and data`* <br/>
`mysqldump -u root -p dbname > dbname.sql`
<br/><br/>

### Import database from `.sql` script file
`mysql -u root -p dbname < dbname.sql`
