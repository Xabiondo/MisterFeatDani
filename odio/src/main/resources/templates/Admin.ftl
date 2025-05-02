<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Panel de Administración - Fantasy Liga</title>
<style>
    /* Estilos Generales */
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

    .container {
      padding: 30px 20px;
      text-align: center;
    }

    h1 {
      color: #FFD700;
      margin-bottom: 20px;
      font-size: 18px;
    }

    h2 {
      color: #FFD700;
      margin: 30px 0 10px;
      font-size: 12px;
    }

    .section {
      background-color: #1e1e2e;
      padding: 20px;
      border-radius: 15px;
      margin-bottom: 20px;
      box-shadow: 0 0 10px #FFD700;
    }

    /* Estilos del formulario */
    .form-inline {
      display: flex;
      justify-content: center;
      gap: 15px;
      margin-bottom: 20px;
    }

    .form-inline input, .form-inline select {
      padding: 8px;
      font-size: 10px;
      border: 1px solid #FFD700;
      border-radius: 6px;
      background-color: #333;
      color: #FFD700;
    }

    .form-inline button {
      padding: 8px 15px;
      font-size: 10px;
      background-color: #00d9ff;
      color: #000;
      border: none;
      border-radius: 10px;
      cursor: pointer;
      box-shadow: 0 0 10px #00d9ff;
    }

    .form-inline button:hover {
      background-color: #000;
      color: #00d9ff;
      box-shadow: 0 0 15px #FFD700;
    }

    /* Estilos de la tabla de usuarios */
    table {
      width: 100%;
      margin-top: 20px;
      border-collapse: collapse;
    }

    table th, table td {
      padding: 10px;
      text-align: center;
      font-size: 10px;
      border: 1px solid #FFD700;
    }

    table th {
      background-color: #333;
      color: #FFD700;
    }

    table td {
      background-color: #1e1e2e;
    }

    .update-btn, .delete-btn {
      padding: 5px 10px;
      font-size: 8px;
      background-color: #00d9ff;
      color: #000;
      border: none;
      border-radius: 6px;
      cursor: pointer;
      box-shadow: 0 0 10px #00d9ff;
    }

    .update-btn:hover, .delete-btn:hover {
      background-color: #000;
      color: #00d9ff;
      box-shadow: 0 0 15px #FFD700;
    }

    .no-users {
      color: #FFD700;
      font-size: 12px;
      font-style: italic;
    }

  </style>

</head>
<body>

<div class="navbar">
    <div><strong style="color: #FFD700;">FantasyFútbol</strong></div>
    <div>
      <a href="/interfaz">Inicio</a>
      <a href="/poner-subasta">Subastar</a>
      <a href="/Mercado">Mercado</a>
      <a href="/Equipo">Mi Equipo</a>
      <a href="/Ayuda">Ayuda</a>
      <a href="/logout">Cerrar sesión</a>
    </div>
  </div>

  <div class="container">
    <h1>Panel de Administración</h1>

    <!-- Sección: Crear nuevo usuario -->
    <div class="section">
      <h2>Crear Usuario con Privilegios</h2>
      <form class="form-inline" method="post" action="/admin/create">
        <input type="text" name="nombre" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <select name="rol">
          <option value="usuario">Usuario</option>
          <option value="admin">Admin</option>
        </select>
        <button type="submit">Crear</button>
      </form>
    </div>

    <!-- Sección: Lista de usuarios y acciones -->
    <div class="section">
      <h2>Usuarios Registrados</h2>
      <#if usuarios?size gt 0>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Dinero (€)</th>
              <th>Eliminar</th>
            </tr>
          </thead>
          <tbody>
            <#list usuarios as usuario>
              <tr>
                <td>${usuario.id}</td>
                <td>${usuario.nombre}</td>
                <td>
                  <form method="post" action="/admin/update/${usuario.id}" style="display:inline;">
                    <input type="number" name="dinero" value="${usuario.dinero?string}" min="0">
                    <button type="submit" class="update-btn">OK</button>
                  </form>
                </td>
                <td>
                  <form method="post" action="/admin/delete/${usuario.id}" onsubmit="return confirm('Eliminar ${usuario.nombre}?');" style="display:inline;">
                    <button type="submit" class="delete-btn">X</button>
                  </form>
                </td>
              </tr>
            </#list>
          </tbody>
        </table>
      <#else>
        <div class="no-users">No hay usuarios registrados.</div>
      </#if>
    </div>

  </div>
</body>
</html>