    <!DOCTYPE html>
    <html lang="es">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Subastar Jugador - FantasyFútbol</title>
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
    max-width: 400px;
    margin: 0 auto;
    box-shadow: 0 0 10px #FFD700aa;
    }
    label {
    display: block;
    margin-top: 15px;
    font-size: 10px;
    }
    select, input[type="number"] {
    width: 90%;
    padding: 8px;
    margin-top: 5px;
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
      <h2>Subastar Jugador</h2>
      <div class="form-container">
        <form action="/subastar-jugador" method="POST">
          <label for="jugador">Selecciona uno de tus jugadores:</label>
          <select name="jugador" id="jugador" required>
            <!-- Iterar sobre el inventario y mostrar cada jugador -->
            <#if inventario?has_content>
              <#list inventario as jugador>
                <option value="${jugador.nombre}">${jugador.nombre} (${jugador.equipo})</option>
              </#list>
            <#else>
              <option disabled>No tienes jugadores disponibles</option>
            </#if>
          </select>

          <label for="precio">Precio de salida (€):</label>
          <input type="number" name="precio" id="precio" min="1" required>

          <button type="submit">Subastar</button>
        </form>
      </div>
    </div>

    </body>
    </html>