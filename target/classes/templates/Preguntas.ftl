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
.pregunta-container {
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
h2 {
color: #FFD700;
margin-bottom: 30px;
}
.dinero {
font-size: 1.2em;
color: #2e7d32;
margin-bottom: 20px;
}
label {
display: block;
margin-top: 15px;
font-size: 10px;
}
input[type="text"] {
width: 90%;
padding: 8px;
margin-top: 10px;
border-radius: 8px;
border: none;
font-family: 'Press Start 2P', cursive;
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
.mensaje {
margin-top: 18px;
color: #FFD700;
font-weight: bold;
font-size: 12px;
}
.volver {
display: block;
margin: 28px auto 0 auto;
color: #FFD700;
text-decoration: underline;
font-size: 10px;
width: fit-content;
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
    </div>
</div>

<div class="main">
    <div class="pregunta-container">
        <h2>Responde y gana dinero</h2>
        <div class="dinero">Dinero disponible: $${dineroDisponible}</div>
        <form method="post" action="/ganar-dinero">
            <p><strong>${pregunta.enunciado}</strong></p>
            <input type="hidden" name="id" value="${pregunta.id}">
            <input type="text" name="respuesta" placeholder="Tu respuesta" required>
            <button type="submit">Responder</button>
        </form>
        <#if mensaje??>
            <div class="mensaje">${mensaje}</div>
        </#if>
        <a href="/interfaz" class="volver">Volver al menú</a>
    </div>
</div>
</body>
</html>