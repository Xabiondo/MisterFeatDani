<#-- Admin.ftl -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración - Fantasy Liga</title>
    <style>
        body {
font-family: Arial, sans-serif;
margin: 20px;
background-color: #f5f5f5;
}
.container {
max-width: 1200px;
margin: 0 auto;
background: white;
padding: 20px;
border-radius: 8px;
box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
table {
width: 100%;
border-collapse: collapse;
margin-top: 20px;
}
th, td {
padding: 12px;
text-align: left;
border-bottom: 1px solid #ddd;
}
th {
background-color: #4CAF50;
color: white;
}
tr:hover {
background-color: #f5f5f5;
}
.delete-btn {
background-color: #ff4444;
color: white;
border: none;
padding: 8px 16px;
border-radius: 4px;
cursor: pointer;
}
.delete-btn:hover {
background-color: #cc0000;
}
</style>
</head>
<body>
<div class="container">
        <h1>Panel de Administración</h1>

        <#if usuarios?size gt 0>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Dinero</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <#list usuarios as usuario>
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.dinero} €</td>
                            <td>
                                <form action="/admin/delete/${usuario.id}" method="POST"
                                      onsubmit="return confirm('¿Estás seguro de eliminar a ${usuario.nombre}?');">
                                    <button type="submit" class="delete-btn">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <p>No hay usuarios registrados.</p>
        </#if>
    </div>
</body>
</html>
