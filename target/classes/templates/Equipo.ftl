    <!DOCTYPE html>
    <html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mi Equipo - FantasyFútbol</title>
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
            body {
                margin: 0;
                font-family: 'Press Start 2P', cursive;
                background: linear-gradient(135deg, #00b140, #1e3c72);
                color: #fff;
            }

            .navbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background: rgba(0, 0, 0, 0.85);
                padding: 15px 25px;
            }

            .navbar .logo-container {
                display: flex;
                align-items: center;
                gap: 12px;
            }

            .navbar img {
                height: 40px;
            }

            .navbar h1 {
                font-size: 16px;
                margin: 0;
                color: #FFD700;
            }

            .navbar .links {
                display: flex;
                gap: 25px;
                font-size: 12px;
            }

            .navbar a {
                text-decoration: none;
                color: #fff;
                transition: color 0.3s;
            }

            .navbar a:hover {
                color: #FFD700;
            }

            .navbar .user {
                font-size: 12px;
                background: #FFD700;
                color: #000;
                padding: 6px 10px;
                border-radius: 8px;
            }

            .container {
                padding: 0px;
                text-align: center;
            }

            .field {
                position: relative;
                background: url('campo-pixel.png') no-repeat center/cover;
                margin: auto auto;
                width: 80%;
                max-width: 700px;
                height: 500px;
                border: 4px solid #FFD700;
                border-radius: 20px;
                margin-bottom: 30px;

            }

            .position {
                position: absolute;
                width: 60px;
                height: 60px;
                border-radius: 50%;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                font-size: 10px;
                text-align: center;
                background: rgba(255, 255, 255, 0.1);
            }

            .position img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-bottom: 5px;
            }

            .gk  { top: 85%; left: 50%; transform: translate(-50%, -50%); }
            .df1 { top: 65%; left: 20%; }
            .df2 { top: 65%; left: 40%; }
            .df3 { top: 65%; left: 60%; }
            .df4 { top: 65%; left: 80%; }
            .mf1 { top: 45%; left: 30%; }
            .mf2 { top: 45%; left: 50%; }
            .mf3 { top: 45%; left: 70%; }
            .fw1 { top: 20%; left: 25%; }
            .fw2 { top: 20%; left: 50%; }
            .fw3 { top: 20%; left: 75%; }

            .players-list {
                background: rgba(0, 0, 0, 0.7);
                padding: 20px;
                max-width: 800px;
                margin: 0 auto;
                border-radius: 12px;
            }

            .players-list h3 {
                font-size: 14px;
                margin-bottom: 15px;
                color: #FFD700;
            }

            .players-list ul {
                list-style: none;
                padding: 0;
                margin: 0;
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }

            .players-list li {
                background: #fff;
                color: #000;
                padding: 6px 12px;
                border-radius: 10px;
                font-size: 10px;
                cursor: pointer;
                transition: transform 0.2s, background 0.2s;
            }

            .players-list li:hover {
                transform: scale(1.1);
                background: #FFD700;
                color: #000;
            }

            .selected {
                background: #FFD700 !important;
                color: #000;
            }

            .error-message {
                color: red;
                font-size: 12px;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>

    <div class="navbar">
        <div class="logo-container">
            <img src="balon-pixelart.png" alt="Logo">
            <h1>FantasyFútbol</h1>
        </div>
        <div class="links">
            <a href="inicio.html">Inicio</a>
            <a href="miequipo.html">Mi Equipo</a>
            <a href="mercado.html">Mercado</a>
            <a href="perfil.html">Perfil</a>
            <a href="ayuda.html">Ayuda</a>
            <a href="contacto.html">Contacto</a>
        </div>
        <div class="user">Dani10</div>
    </div>

    <div class="container">
        <h2>Tu 11 inicial</h2>
        <div class="field">
            <div class="position gk" id="posicion-gk"></div>
            <div class="position df1" id="posicion-df1"></div>
            <div class="position df2" id="posicion-df2"></div>
            <div class="position df3" id="posicion-df3"></div>
            <div class="position df4" id="posicion-df4"></div>
            <div class="position mf1" id="posicion-mf1"></div>
            <div class="position mf2" id="posicion-mf2"></div>
            <div class="position mf3" id="posicion-mf3"></div>
            <div class="position fw1" id="posicion-fw1"></div>
            <div class="position fw2" id="posicion-fw2"></div>
            <div class="position fw3" id="posicion-fw3"></div>
        </div>
        <div class="players-list">
            <h3>Selecciona tu equipo</h3>
            <p>Límite: 1 portero, 4 defensas, 3 centrocampistas y 3 delanteros.</p>
            <div id="jugadores-container"></div>
            <div class="error-message" id="error-message"></div>
        </div>
    </div>

    <script>
    const jugadores = [
        <#if inventario?has_content>
            <#list inventario as jugador>
                { nombre: "${jugador.nombre}", posicion: "${jugador.posicion}" }<#if jugador_has_next>,</#if>
            </#list>
        </#if>
    ];
        const equipo = {
            portero: [],
            defensa: [],
            centrocampista: [],
            delantero: []
        };

        const limitePosiciones = {
            portero: 1,
            defensa: 4,
            centrocampista: 3,
            delantero: 3
        };

        const posicionesCampo = {
            portero: ["posicion-gk"],
            defensa: ["posicion-df1", "posicion-df2", "posicion-df3", "posicion-df4"],
            centrocampista: ["posicion-mf1", "posicion-mf2", "posicion-mf3"],
            delantero: ["posicion-fw1", "posicion-fw2", "posicion-fw3"]
        };

        function mostrarJugadoresDisponibles(jugadores) {
            const contenedor = document.getElementById("jugadores-container");
            contenedor.innerHTML = "";

            jugadores.forEach((jugador, index) => {
                const item = document.createElement("li");
                item.textContent = jugador.nombre;
                item.dataset.posicion = jugador.posicion;
                item.dataset.index = index;
                item.addEventListener("click", () => seleccionarJugador(index));
                contenedor.appendChild(item);
            });
        }

        function seleccionarJugador(index) {
            const jugador = jugadores[index];
            const posicion = jugador.posicion;
            const indexEnEquipo = equipo[posicion].findIndex(j => j.nombre === jugador.nombre);

            if (indexEnEquipo !== -1) {
                equipo[posicion].splice(indexEnEquipo, 1);
            } else {
                if (equipo[posicion].length >= limitePosiciones[posicion]) {
                    document.getElementById("error-message").textContent = `Ya tienes el máximo de  permitidos.`;
                    return;
                }
                equipo[posicion].push(jugador);
            }

            actualizarInterfaz();
            actualizarCampo();
        }

        function actualizarInterfaz() {
            document.getElementById("error-message").textContent = "";
            const items = document.querySelectorAll("#jugadores-container li");
            items.forEach(item => {
                const index = item.dataset.index;
                const jugador = jugadores[index];
                const posicion = jugador.posicion;
                if (equipo[posicion].some(j => j.nombre === jugador.nombre)) {
                    item.classList.add("selected");
                } else {
                    item.classList.remove("selected");
                }
            });
        }

        function actualizarCampo() {
            Object.values(posicionesCampo).flat().forEach(id => {
                const posicionCampo = document.getElementById(id);
                posicionCampo.innerHTML = "";
            });

            for (const [posicion, jugadoresPosicion] of Object.entries(equipo)) {
                jugadoresPosicion.forEach((jugador, index) => {
                    const idPosicion = posicionesCampo[posicion][index];
                    const posicionCampo = document.getElementById(idPosicion);
                    const img = document.createElement("img");
                    img.src = `jpg`;
                    img.alt = jugador.nombre;
                    const nombre = document.createElement("div");
                    nombre.textContent = jugador.nombre;
                    posicionCampo.appendChild(img);
                    posicionCampo.appendChild(nombre);
                });
            }
        }

        window.onload = () => {
            mostrarJugadoresDisponibles(jugadores);
        };
    </script>

    </body>
    </html>