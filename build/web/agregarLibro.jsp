<%-- 
    Document   : agregarLibro
    Created on : 9/11/2024, 7:32:39 a. m.
    Author     : mmora
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Libro</title>
    <script>
        function cargarPastas() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'PastasJSONServlet', true);
            xhr.onload = function() {
                if (this.status === 200) {
                    var pastas = JSON.parse(this.responseText);
                    var select = document.getElementById("pasta");
                    pastas.forEach(function(pasta) {
                        var option = document.createElement("option");
                        option.value = pasta.id;
                        option.text = pasta.descripcion;
                        select.add(option);
                    });
                }
            };
            xhr.send();
        }
        window.onload = cargarPastas;
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">El Libraco</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Agregar Libro <span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="mostarLibros.jsp">Mostar Libros</a>
      </li>

    </ul>
  </div>
</nav>
    <div class="container mt-5">
        <h1 class="text-center">Agregar Libro</h1>
        
        <form action="RegistrarLibro" method="post" class="mt-4">
            <div class="mb-3">
                <label for="bodega" class="form-label">ISBN:</label>
                <input type="text" name="isbn" id="isbn" required>
            </div>
            <div class="mb-3">
                <label for="bodega" class="form-label">Titulo</label>
                <input type="text" name="titulo" id="titulo" required>
            </div>
            <div class="mb-3">
                <label for="bodega" class="form-label">Anio de Publicacion:</label>
                <input type="text" name="anio" id="anio" required>
            </div>
            <div class="mb-3">
                <label for="bodega" class="form-label">Autor:</label>
                <input type="text" name="autor" id="autor" required>
            </div>
            <div class="mb-3">
                <label for="bodega" class="form-label">Clasificacion:</label>
                <input type="text" name="clasificacion" id="clasificacion" required>
            </div>
            <div class="mb-3">
                <label for="bodega" class="form-label">Numero de Paginas:</label>
                <input type="text" name="paginas" id="paginas" required>
            </div>     
            <div class="mb-3">
                <label for="libro" class="form-label">Seleccione el tipo de pasta</label>
                <select name="pasta" id="pasta">

                </select>
            </div>            
            <input type="submit" class="btn btn-primary" value="Agregar Libro">
        </form>
   </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>    
</body>
</html>
