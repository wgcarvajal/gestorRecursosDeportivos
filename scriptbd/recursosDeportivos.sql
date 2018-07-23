-- phpMyAdmin SQL Dump
-- version 4.6.5.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 23-07-2018 a las 22:17:30
-- Versión del servidor: 5.7.16
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `recursosDeportivos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CARGO`
--

CREATE TABLE `CARGO` (
  `CARID` int(11) NOT NULL,
  `CARNOMBRE` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DEPORTE`
--

CREATE TABLE `DEPORTE` (
  `DEPID` int(11) NOT NULL,
  `DEPNOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ENTIDADPARTICULAR`
--

CREATE TABLE `ENTIDADPARTICULAR` (
  `ENTID` int(11) NOT NULL,
  `ENTNOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ESCENARIO`
--

CREATE TABLE `ESCENARIO` (
  `ESCID` int(11) NOT NULL,
  `ESCNOMBRE` varchar(100) NOT NULL,
  `ESCANCHOIMAGEN` int(11) NOT NULL,
  `ESCLARGOIMAGEN` int(11) NOT NULL,
  `ESCNOMBREIMAGENANIMADA` varchar(50) NOT NULL,
  `ESCNOMBREIMAGENREAL` varchar(50) DEFAULT NULL,
  `ESCMEDIDAS` varchar(50) DEFAULT NULL,
  `ESCDESCRIPCION` text,
  `ESCRECOMENDACIONES` text,
  `BORDE` int(11) DEFAULT NULL,
  `ESCMARGENSUPERIOR` int(11) NOT NULL,
  `ESCMARGENIZQUIERDA` int(11) NOT NULL,
  `ESCROTARIMAGEN` int(11) NOT NULL,
  `ESCPOSICION` int(11) NOT NULL,
  `ESCVECESRESERVADIA` int(11) DEFAULT NULL,
  `ESCDURACIONRESERVA` int(11) DEFAULT NULL,
  `ESPID` int(11) DEFAULT NULL,
  `ESCTIPOESCID` int(11) DEFAULT NULL,
  `ESCESTESCID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ESCENARIO`
--

INSERT INTO `ESCENARIO` (`ESCID`, `ESCNOMBRE`, `ESCANCHOIMAGEN`, `ESCLARGOIMAGEN`, `ESCNOMBREIMAGENANIMADA`, `ESCNOMBREIMAGENREAL`, `ESCMEDIDAS`, `ESCDESCRIPCION`, `ESCRECOMENDACIONES`, `BORDE`, `ESCMARGENSUPERIOR`, `ESCMARGENIZQUIERDA`, `ESCROTARIMAGEN`, `ESCPOSICION`, `ESCVECESRESERVADIA`, `ESCDURACIONRESERVA`, `ESPID`, `ESCTIPOESCID`, `ESCESTESCID`) VALUES
(4, 'Coliseo', 250, 132, 'esc4_1.png', 'vacio', NULL, NULL, NULL, 0, 21, 416, 6, 1, 1, 60, 1, 1, 1),
(5, 'Cancha7', 100, 25, 'esc5_1.png', 'vacio', NULL, NULL, NULL, 0, 21, 14, 334, 2, 1, 60, 1, 1, 1),
(6, 'cancha1', 100, 89, 'esc6_1.png', 'vacio', NULL, NULL, NULL, 0, 176, 831, 0, 3, 1, 60, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ESPACIOESCENARIOS`
--

CREATE TABLE `ESPACIOESCENARIOS` (
  `ESPID` int(11) NOT NULL,
  `ESPNOMBRE` varchar(100) NOT NULL,
  `ESPANCHO` int(11) NOT NULL,
  `ESPDIRECCION` varchar(50) DEFAULT NULL,
  `ESPDESCRIPCION` text,
  `ESPCOLOR` varchar(50) DEFAULT NULL,
  `ESPNOMBREIMAGEN` varchar(50) DEFAULT NULL,
  `ESPHORAINICIO` int(11) DEFAULT NULL,
  `ESPHORAFIN` int(11) DEFAULT NULL,
  `ESPRESEVHORASEMANA` int(11) DEFAULT NULL,
  `ESPRESEVHORADIA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ESPACIOESCENARIOS`
--

INSERT INTO `ESPACIOESCENARIOS` (`ESPID`, `ESPNOMBRE`, `ESPANCHO`, `ESPDIRECCION`, `ESPDESCRIPCION`, `ESPCOLOR`, `ESPNOMBREIMAGEN`, `ESPHORAINICIO`, `ESPHORAFIN`, `ESPRESEVHORASEMANA`, `ESPRESEVHORADIA`) VALUES
(1, 'CDU', 441, NULL, NULL, '#BCF5A9', NULL, 7, 22, 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ESTADOESCENARIO`
--

CREATE TABLE `ESTADOESCENARIO` (
  `ESTESCID` int(11) NOT NULL,
  `ESTESCNOMBRE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ESTADOESCENARIO`
--

INSERT INTO `ESTADOESCENARIO` (`ESTESCID`, `ESTESCNOMBRE`) VALUES
(1, 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GRUPO`
--

CREATE TABLE `GRUPO` (
  `gruid` varchar(20) NOT NULL,
  `GRUDESCRIPCION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `GRUPO`
--

INSERT INTO `GRUPO` (`gruid`, `GRUDESCRIPCION`) VALUES
('admin', 'administrador de plataforma'),
('user', 'usuario plataforma');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TIPOESCENARIO`
--

CREATE TABLE `TIPOESCENARIO` (
  `TIPOESCID` int(11) NOT NULL,
  `TIPOESCNOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `TIPOESCENARIO`
--

INSERT INTO `TIPOESCENARIO` (`TIPOESCID`, `TIPOESCNOMBRE`) VALUES
(1, 'RESERVA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TIPOUNIDADACADEMICA`
--

CREATE TABLE `TIPOUNIDADACADEMICA` (
  `TIPID` int(11) NOT NULL,
  `TIPNOMBRE` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UNIDADACADEMICA`
--

CREATE TABLE `UNIDADACADEMICA` (
  `UNIID` int(11) NOT NULL,
  `UNINOMBRE` varchar(75) NOT NULL,
  `TIPID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIO`
--

CREATE TABLE `USUARIO` (
  `USUID` int(20) NOT NULL,
  `USUIDENTIFICACION` int(20) NOT NULL,
  `USUFECHANACIMIENTO` date NOT NULL,
  `USUNOMBRES` varchar(75) NOT NULL,
  `USUAPELLIDOS` varchar(75) NOT NULL,
  `USUGENERO` char(1) NOT NULL,
  `USUNOMBREUSUARIO` varchar(75) NOT NULL,
  `USUCONTRASENA` varchar(250) NOT NULL,
  `USUEMAIL` varchar(150) NOT NULL,
  `USUTELEFONO` bigint(20) NOT NULL,
  `USUFOTO` varchar(150) DEFAULT NULL,
  `CARID` int(11) DEFAULT NULL,
  `UNIID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `USUARIO`
--

INSERT INTO `USUARIO` (`USUID`, `USUIDENTIFICACION`, `USUFECHANACIMIENTO`, `USUNOMBRES`, `USUAPELLIDOS`, `USUGENERO`, `USUNOMBREUSUARIO`, `USUCONTRASENA`, `USUEMAIL`, `USUTELEFONO`, `USUFOTO`, `CARID`, `UNIID`) VALUES
(2, 1075220291, '1986-12-09', 'Wilson Geovanny', 'Carvajal Molina', 'M', 'wgcarvajal', '8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92', 'wilnacio@hotmail.com', 32432423, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIOGRUPO`
--

CREATE TABLE `USUARIOGRUPO` (
  `GRUID` varchar(20) NOT NULL,
  `USUID` int(11) NOT NULL,
  `USUNOMBREUSUARIO` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `USUARIOGRUPO`
--

INSERT INTO `USUARIOGRUPO` (`GRUID`, `USUID`, `USUNOMBREUSUARIO`) VALUES
('admin', 2, 'wgcarvajal');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `CARGO`
--
ALTER TABLE `CARGO`
  ADD PRIMARY KEY (`CARID`);

--
-- Indices de la tabla `DEPORTE`
--
ALTER TABLE `DEPORTE`
  ADD PRIMARY KEY (`DEPID`);

--
-- Indices de la tabla `ENTIDADPARTICULAR`
--
ALTER TABLE `ENTIDADPARTICULAR`
  ADD PRIMARY KEY (`ENTID`);

--
-- Indices de la tabla `ESCENARIO`
--
ALTER TABLE `ESCENARIO`
  ADD PRIMARY KEY (`ESCID`),
  ADD KEY `fk_espacioescenario_escenario` (`ESPID`),
  ADD KEY `fk_tipoescenario_escenario` (`ESCTIPOESCID`),
  ADD KEY `fk_estadoescenario_escenario` (`ESCESTESCID`);

--
-- Indices de la tabla `ESPACIOESCENARIOS`
--
ALTER TABLE `ESPACIOESCENARIOS`
  ADD PRIMARY KEY (`ESPID`);

--
-- Indices de la tabla `ESTADOESCENARIO`
--
ALTER TABLE `ESTADOESCENARIO`
  ADD PRIMARY KEY (`ESTESCID`);

--
-- Indices de la tabla `GRUPO`
--
ALTER TABLE `GRUPO`
  ADD PRIMARY KEY (`gruid`);

--
-- Indices de la tabla `TIPOESCENARIO`
--
ALTER TABLE `TIPOESCENARIO`
  ADD PRIMARY KEY (`TIPOESCID`);

--
-- Indices de la tabla `TIPOUNIDADACADEMICA`
--
ALTER TABLE `TIPOUNIDADACADEMICA`
  ADD PRIMARY KEY (`TIPID`);

--
-- Indices de la tabla `UNIDADACADEMICA`
--
ALTER TABLE `UNIDADACADEMICA`
  ADD PRIMARY KEY (`UNIID`),
  ADD KEY `fk_tipounidadacademica_unidadacademica` (`TIPID`);

--
-- Indices de la tabla `USUARIO`
--
ALTER TABLE `USUARIO`
  ADD PRIMARY KEY (`USUID`),
  ADD KEY `fk_cargo_usuario` (`CARID`),
  ADD KEY `fk_unidadacademica_usuario` (`UNIID`);

--
-- Indices de la tabla `USUARIOGRUPO`
--
ALTER TABLE `USUARIOGRUPO`
  ADD PRIMARY KEY (`USUNOMBREUSUARIO`),
  ADD KEY `fk_grupo_usuariogrupo` (`GRUID`),
  ADD KEY `fk_usuario_usuariogrupo` (`USUID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `CARGO`
--
ALTER TABLE `CARGO`
  MODIFY `CARID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `DEPORTE`
--
ALTER TABLE `DEPORTE`
  MODIFY `DEPID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ENTIDADPARTICULAR`
--
ALTER TABLE `ENTIDADPARTICULAR`
  MODIFY `ENTID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ESCENARIO`
--
ALTER TABLE `ESCENARIO`
  MODIFY `ESCID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `ESPACIOESCENARIOS`
--
ALTER TABLE `ESPACIOESCENARIOS`
  MODIFY `ESPID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `ESTADOESCENARIO`
--
ALTER TABLE `ESTADOESCENARIO`
  MODIFY `ESTESCID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `TIPOESCENARIO`
--
ALTER TABLE `TIPOESCENARIO`
  MODIFY `TIPOESCID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `TIPOUNIDADACADEMICA`
--
ALTER TABLE `TIPOUNIDADACADEMICA`
  MODIFY `TIPID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `UNIDADACADEMICA`
--
ALTER TABLE `UNIDADACADEMICA`
  MODIFY `UNIID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `USUARIO`
--
ALTER TABLE `USUARIO`
  MODIFY `USUID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ESCENARIO`
--
ALTER TABLE `ESCENARIO`
  ADD CONSTRAINT `fk_espacioescenario_escenario` FOREIGN KEY (`ESPID`) REFERENCES `ESPACIOESCENARIOS` (`ESPID`),
  ADD CONSTRAINT `fk_estadoescenario_escenario` FOREIGN KEY (`ESCESTESCID`) REFERENCES `ESTADOESCENARIO` (`ESTESCID`),
  ADD CONSTRAINT `fk_tipoescenario_escenario` FOREIGN KEY (`ESCTIPOESCID`) REFERENCES `TIPOESCENARIO` (`TIPOESCID`);

--
-- Filtros para la tabla `UNIDADACADEMICA`
--
ALTER TABLE `UNIDADACADEMICA`
  ADD CONSTRAINT `fk_tipounidadacademica_unidadacademica` FOREIGN KEY (`TIPID`) REFERENCES `TIPOUNIDADACADEMICA` (`TIPID`);

--
-- Filtros para la tabla `USUARIO`
--
ALTER TABLE `USUARIO`
  ADD CONSTRAINT `fk_cargo_usuario` FOREIGN KEY (`CARID`) REFERENCES `CARGO` (`CARID`),
  ADD CONSTRAINT `fk_unidadacademica_usuario` FOREIGN KEY (`UNIID`) REFERENCES `UNIDADACADEMICA` (`UNIID`);

--
-- Filtros para la tabla `USUARIOGRUPO`
--
ALTER TABLE `USUARIOGRUPO`
  ADD CONSTRAINT `fk_grupo_usuariogrupo` FOREIGN KEY (`GRUID`) REFERENCES `GRUPO` (`gruid`),
  ADD CONSTRAINT `fk_usuario_usuariogrupo` FOREIGN KEY (`USUID`) REFERENCES `USUARIO` (`USUID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
