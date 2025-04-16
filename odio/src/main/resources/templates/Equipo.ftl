<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Equipo - FantasyFútbol</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

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
    const jugadores = [             // Estos jugadores son jugadores de ejemplo, cuando se implemente al codigo serán jugadores de la actual liga
        { nombre: "Casillas", posicion: "portero" },
        { nombre: "Ramos", posicion: "defensa" },
        { nombre: "Piqué", posicion: "defensa" },
        { nombre: "Alba", posicion: "defensa" },
        { nombre: "Carvajal", posicion: "defensa" },
        { nombre: "Iniesta", posicion: "centrocampista" },
        { nombre: "Xavi", posicion: "centrocampista" },
        { nombre: "Busquets", posicion: "centrocampista" },
        { nombre: "Messi", posicion: "delantero" },
        { nombre: "Suárez", posicion: "delantero" },
        { nombre: "Neymar", posicion: "delantero" },
        { nombre: "De Gea", posicion: "portero" },
        { nombre: "Varane", posicion: "defensa" },
        { nombre: "Marcelo", posicion: "defensa" },
        { nombre: "Kroos", posicion: "centrocampista" },
        { nombre: "Modric", posicion: "centrocampista" },
        { nombre: "Benzema", posicion: "delantero" },
        { nombre: "Griezmann", posicion: "delantero" },
        { nombre: "Hazard", posicion: "delantero" },
        { nombre: "Lewandowski", posicion: "delantero" },
        { nombre: "Van Dijk", posicion: "defensa" }
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