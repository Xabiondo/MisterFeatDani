<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mercado de Jugadores</title>
  <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
  <style>
    body {
margin: 0;
font-family: 'Press Start 2P', cursive;
background-color: #101820;
color: #ffffff;
padding-bottom: 50px;
}
.navbar {
display: flex;
justify-content: space-between;
align-items: center;
background-color: #000;
padding: 15px 25px;
border-bottom: 3px solid #FFD700;
}
.navbar a {
text-decoration: none;
color: #FFD700;
margin: 0 10px;
font-size: 10px;
}
.main {
padding: 30px 20px;
text-align: center;
}
h2 {
color: #FFD700;
margin-bottom: 30px;
}
.form-container {
background-color: #1e1e2e;
border: 2px solid #FFD700;
border-radius: 10px;
padding: 30px;
width: 90%;
max-width: 1000px;
margin: 0 auto;
box-shadow: 0 0 10px #FFD700aa;
text-align: center;
}
label {
display: block;
margin-top: 15px;
font-size: 10px;
}
input[type="text"] {
width: 90%;
padding: 8px;
margin-top: 5px;
border-radius: 8px;
border: none;
font-family: 'Press Start 2P', cursive;
font-size: 10px;
}
.options {
margin-top: 15px;
display: flex;
justify-content: space-between;
align-items: center;
font-size: 10px;
}
button {
margin-top: 20px;
background-color: #FFD700;
color: #000;
border: none;
padding: 10px 20px;
border-radius: 8px;
font-size: 10px;
cursor: pointer;
font-family: 'Press Start 2P', cursive;
transition: all 0.3s ease;
}
button:hover {
background-color: #000;
color: #FFD700;
box-shadow: 0 0 10px #00d9ff;
}
table {
width: 100%;
border-collapse: collapse;
margin-top: 20px;
background: #fff;
color: #2c3e50;
}
th, td {
padding: 10px;
border: 1px solid #bbb;
font-size: 12px;
}
th {
background: #34495e;
color: #fff;
}
tr:nth-child(even) {
background: #e1ecf4;
}
tr:hover {
background: #d0e4f4;
}
.action-form button {
font-size: 10px;
padding: 6px 14px;
}
.register-link {
margin-top: 20px;
font-size: 10px;
}
.register-link a {
color: #FFD700;
}
</style>
</head>
<body>

<div class="navbar">
    <div><strong style="color: #FFD700;">FantasyFútbol</strong></div>
    <div>
         <a href="/interfaz">Inicio</a>
         <a href="/Equipo">Mi Equipo</a>
         <a href="/Mercado">Mercado</a>
         <a href="/poner-subasta">Subastar</a>
         <a href="/Ayuda">Ayuda</a>
         <a href="/Preguntas">Palancas económicas</a>
         <a href="/logout">Cerrar sesión</a>

    </div>
  </div>

  <div class="main">
    <div class="form-container">
      <h2>Mercado de Jugadores</h2>
      <p>¡Hola, ${nombreUsuario}! Dinero disponible: $${dineroDisponible}</p>

      <form action="/buscar" method="get">
        <label for="nombre">Buscar jugador:</label>
        <input type="text" id="nombre" name="nombre" placeholder="Ej: Messi">
        <button type="submit">Buscar</button>
      </form>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Jugador</th>
            <th>Equipo</th>
            <th>posicion</th>
            <th>Precio</th>
            <th>Inicio</th>
            <th>Vendedor</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          <#list subastas as subasta>
            <tr>
              <td>${subasta.id}</td>
              <td>${subasta.jugador.nombre}</td>
              <td>${subasta.jugador.equipo}</td>
              <td>${subasta.jugador.posicion}</td>
              <td>${subasta.precioSalida} €</td>

              <td>
                <#if subasta.fechaInicio??>${subasta.fechaInicio}
                <#else><em>–</em></#if>
              </td>
              <td>
                <#if subasta.vendedor??>${subasta.vendedor.nombre}
                <#else><em>–</em></#if>
              </td>
              <td>
                <form class="action-form" action="/Mercado" method="post">
                  <input type="hidden" name="subastaId" value="${subasta.id}">
                  <button type="submit">Comprar</button>
                </form>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
    </div>
  </div>

</body>
</html>