<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${title}</title>
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
.menu-container {
background-color: #1e1e2e;
border: 2px solid #FFD700;
border-radius: 10px;
padding: 30px;
width: 90%;
max-width: 400px;
margin: 40px auto;
box-shadow: 0 0 10px #FFD700aa;
text-align: center;
}
h1 {
color: #FFD700;
margin-bottom: 30px;
font-size: 1.1em;
}
p {
font-size: 11px;
margin-bottom: 25px;
}
.menu-button {
display: block;
width: 100%;
margin: 12px 0;
background-color: #FFD700;
color: #000;
border: none;
padding: 12px 0;
border-radius: 8px;
font-size: 10px;
cursor: pointer;
font-family: 'Press Start 2P', cursive;
transition: all 0.3s ease;
text-align: center;
text-decoration: none;
}
.menu-button a {
color: inherit;
text-decoration: none;
display: block;
width: 100%;
height: 100%;
}
.menu-button:hover {
background-color: #000;
color: #FFD700;
box-shadow: 0 0 10px #00d9ff;
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
   <a href="/logout">Cerrar sesión</a>
  <#if rol?exists && rol == "admin">
    <a href="/admin">Admin Panel</a>
  </#if>
  </div>
</div>

<div class="main">
  <div class="menu-container">
    <h1>¡Hola, ${nombreUsuario}!</h1>
    <p>Tu dinero disponible es: $${dineroDisponible}</p>

    <button class="menu-button"><a href="/poner-subasta">Poner jugador a subasta</a></button>
    <button class="menu-button"><a href="/Mercado">Mercado</a></button>
    <button class="menu-button"><a href="/Equipo">Ver mi equipo</a></button>
    <button class="menu-button"><a href="/Ayuda">Ayuda</a></button>
    <button class="menu-button"><a href="/logout">Cerrar sesión</a></button>
    <button class="menu-button"><a href="/Preguntas">Financiarse</a></button>
  </div>
</div>

</body>
</html>