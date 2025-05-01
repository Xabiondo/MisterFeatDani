<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mercado de Jugadores de Fútbol</title>
    <style>
        body {
font-family: Arial, sans-serif;
background: #f5f5f5;
margin: 0;
padding: 0;
}
        h1, h2 {
text-align: center;
color: #2c3e50;
}
        table {
margin: 30px auto;
border-collapse: collapse;
width: 90%;
background: #fff;
box-shadow: 0 2px 8px #ccc;
}
        th, td {
border: 1px solid #bbb;
padding: 10px 12px;
text-align: center;
}
        th {
background: #34495e;
color: #fff;
}
        tr:nth-child(even) {
background: #f0f4f8;
}
        tr:hover {
background: #e1ecf4;
}
        form {
display: flex;
justify-content: center;
margin: 20px 0;
}
        label {
margin-right: 10px;
}
        input[type="text"] {
padding: 5px;
margin-right: 10px;
border: 1px solid #bbb;
border-radius: 4px;
}
        button {
padding: 6px 14px;
background: #2980b9;
color: #fff;
border: none;
border-radius: 4px;
cursor: pointer;
transition: background 0.2s;
}
        button:hover {
background: #1c5980;
}
        footer {
text-align: center;
margin: 40px 0 10px 0;
color: #888;
}
    </style>
</head>
<body>
    <h1>Mercado de Jugadores de Fútbol</h1>

    <!-- Formulario de búsqueda -->
    <h2>Buscar Subasta</h2>
    <form action="/buscar" method="get">
        <label for="nombre">Nombre del jugador:</label>
        <input type="text" id="nombre" name="nombre" placeholder="Ej: Lionel Messi">
        <button type="submit">Buscar</button>
    </form>

    <!-- Lista de subastas activas -->
    <h2>Subastas Activas</h2>
    <table>
        <thead>
            <tr>
                <th>ID Subasta</th>
                <th>Jugador</th>
                <th>Equipo del Jugador</th>
                <th>Precio de Salida</th>
                <th>Fecha de Inicio</th>
                <th>Vendedor</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iteramos sobre la lista de subastas -->
            <#list subastas as subasta>
                <tr>
                    <td>${subasta.id}</td>
                    <td>${subasta.jugador.nombre}</td>
                    <td>${subasta.jugador.equipo}</td>
                    <td>${subasta.precioSalida} €</td>
                    <!-- Mostrar la fecha de inicio -->
                    <td>
                        <#if subasta.fechaInicio??>
                            ${subasta.fechaInicio}
                        <#else>
                            <em>Sin fecha</em>
                        </#if>
                    </td>
                    <!-- Mostrar el nombre del vendedor de forma segura -->
                    <td>
                        <#if subasta.vendedor??>
                            ${subasta.vendedor.nombre}
                        <#else>
                            <em>Sin vendedor</em>
                        </#if>
                    </td>
                    <td>
                        <form action="/Mercado" method="post" style="margin:0;">
                            <input type="hidden" name="subastaId" value="${subasta.id}">
                            <button type="submit">Comprar</button>
                        </form>
                    </td>


                </tr>
            </#list>
        </tbody>
    </table>

    <!-- Pie de página -->
    <footer>
        <p>&copy; 2023 Mercado de Jugadores de Fútbol. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
