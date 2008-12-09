-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Dez 09, 2008 as 05:07 PM
-- Versão do Servidor: 5.0.67
-- Versão do PHP: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `forca`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `ranking`
--

CREATE TABLE IF NOT EXISTS `ranking` (
  `id` int(11) NOT NULL auto_increment,
  `nome` varchar(60) NOT NULL,
  `nivel` varchar(1) NOT NULL,
  `pontos` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `ranking`
--

INSERT INTO `ranking` (`id`, `nome`, `nivel`, `pontos`) VALUES
(1, 'Daniel', '1', 2),
(2, 'd2', '1', 2),
(3, 'qwerty', '1', 1),
(4, 'Daniel', '1', 4),
(5, 'Daniel', '1', 4);
