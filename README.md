# 🍷 Vinoteca API - Rincon Del Vino

API RESTful para la gestión de **Vinos** y **Ofertas**, desarrollada en **Spring Boot + MySQL**.  
Incluye operaciones CRUD para vinos y ofertas, manejo de relaciones y filtros de ofertas activas.

---

## 🚀 Tecnologías
- Java 17+
- Spring Boot 3+
- Spring Data JPA
- MySQL (persistente)
- H2 (opcional para pruebas)
- Maven

---

## 📂 Estructura principal

src/main/java/com/Vinoteca/AppVinos
│── controller # Controladores REST
│── models # Entidades JPA (Vinos, Oferta, etc.)
│── repository # Repositorios JPA
│── service # Lógica de negocio


---

## 📌 Endpoints disponibles

### 🥂 Vinos s
| Método | Endpoint           | Descripción |
|--------|-------------------|-------------|
| GET    | `/api/vinos`      | Lista todos los vinos |
| GET    | `/api/vinos/{id}` | Busca un vino por ID |
| POST   | `/api/vinos`      | Crea un nuevo vino |
| PUT    | `/api/vinos/{id}` | Actualiza un vino existente |
| DELETE | `/api/vinos/{id}` | Elimina un vino |

###  Bodegas
| Método | Endpoint                       | Descripción                           |
| ------ | ------------------------------ | ------------------------------------- |
| GET    | `/api/ofertas/ofertas/activas` | Lista las ofertas activas según fecha |
| POST   | `/api/ofertas/agregarOferta`   | Crea una nueva oferta                 |
| PUT    | `/api/ofertas/editar/{id}`     | Edita una oferta existente            |
| DELETE | `/api/ofertas/eliminar/{id}`   | Elimina una oferta                    |

✅ Futuras mejoras

Autenticación con JWT.

Roles de administrador / usuario.

Integración con frontend React.

Carrito de compras y pedidos.
