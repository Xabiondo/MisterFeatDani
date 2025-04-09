<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Empleado</title>
    <style>
        body {
     font-family: 'Creepster', cursive; /* Fuente escalofriante */
     background-color: #000; /* Fondo negro */
     background-image: url('https://i.imgur.com/8y9vZ6z.jpg'); /* Imagen de fondo con temática de Halloween */
     background-size: cover;
     background-position: center;
     color: #fff; /* Texto blanco */
     display: flex;
     justify-content: center;
     align-items: center;
     height: 100vh;
     margin: 0;
     overflow: hidden;
 }

 .form-container {
     background: rgba(0, 0, 0, 0.8); /* Fondo semi-transparente */
     padding: 20px;
     border-radius: 8px;
     box-shadow: 0 0 20px rgba(255, 0, 0, 0.5); /* Sombra roja siniestra */
     width: 350px;
     position: relative;
     overflow: hidden;
 }

 h1 {
     text-align: center;
     margin-bottom: 20px;
     font-size: 2.5rem;
     text-shadow: 2px 2px 5px red; /* Sombra roja en el título */
     animation: flicker 1.5s infinite alternate; /* Parpadeo espeluznante */
 }

 label {
     display: block;
     margin-bottom: 5px;
     font-weight: bold;
     text-shadow: 1px 1px 3px red; /* Sombra roja en las etiquetas */
 }

 input[type="text"],
 input[type="number"],
 select {
     width: 100%;
     padding: 8px;
     margin-bottom: 15px;
     border: 1px solid #ff0000; /* Borde rojo */
     border-radius: 4px;
     background-color: #1a1a1a; /* Fondo oscuro */
     color: #fff; /* Texto blanco */
     font-family: 'Creepster', cursive;
 }

 input[type="text"]:focus,
 input[type="number"]:focus,
 select:focus {
     outline: none;
     box-shadow: 0 0 10px red; /* Resplandor rojo al enfocar */
 }

 .radio-group {
     margin-bottom: 15px;
 }

 .radio-group label {
     margin-right: 10px;
     text-shadow: 1px 1px 3px red; /* Sombra roja en los botones de radio */
 }

 button {
     width: 100%;
     padding: 10px;
     background-color: #ff0000; /* Rojo sangre */
     color: white;
     border: none;
     border-radius: 4px;
     cursor: pointer;
     font-size: 16px;
     font-family: 'Creepster', cursive;
     transition: transform 0.3s ease, box-shadow 0.3s ease;
 }

 button:hover {
     background-color: #8b0000; /* Rojo más oscuro al pasar el mouse */
     transform: scale(1.05); /* Efecto de agrandamiento */
     box-shadow: 0 0 15px red; /* Resplandor rojo */
 }

 /* Animación de parpadeo para el título */
 @keyframes flicker {
     0%, 18%, 22%, 25%, 53%, 57%, 100% {
         text-shadow: 2px 2px 5px red;
     }
     20%, 24%, 55% {
         text-shadow: none;
     }
 }

 /* Añadir una animación de neblina en el fondo */
 body::before {
     content: '';
     position: absolute;
     top: 0;
     left: 0;
     width: 200%;
     height: 200%;
     background: url('https://i.imgur.com/xyz1234.png') repeat; /* Imagen de neblina */
     animation: fog-move 15s linear infinite;
     opacity: 0.5;
     z-index: -1;
 }

 @keyframes fog-move {
     0% {
         transform: translateX(0) translateY(0);
     }
     100% {
         transform: translateX(-50%) translateY(-50%);
     }
 }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Formulario de Empleado</h1>
    <form action="/submit" method="POST">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" placeholder="Ingrese el ID" required>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre" required>

        <label for="sueldo">Sueldo:</label>
        <input type="number" id="sueldo" name="sueldo" placeholder="Ingrese el sueldo" required>

        <label>Tipo de Contrato:</label>
        <div class="radio-group">
            <label><input type="radio" name="contrato" value="Temporal" required> Temporal</label>
            <label><input type="radio" name="contrato" value="Indefinido"> Indefinido</label>
        </div>

        <label for="departamento">Departamento:</label>
        <select id="departamento" name="departamento" required>
            <option value="">Seleccione un departamento</option>
            <option value="Recursos Humanos">Recursos Humanos</option>
            <option value="Finanzas">Finanzas</option>
            <option value="Ventas">Ventas</option>
            <option value="Marketing">Marketing</option>
            <option value="Tecnología">Tecnología</option>
            <option value="Atención al Cliente">Atención al Cliente</option>
        </select>

        <button type="submit">Enviar</button>
    </form>
</div>
</body>
</html>