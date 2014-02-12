
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <title>Portal General - Home</title>
        <link type="text/css" rel="stylesheet" href="home.css">
        <%@include file="../includes.html" %>
        <script type="text/javascript" src="../js/simulate.js"></script>
        <script type="text/javascript" src="home.js"></script>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <div class="main" style="text-align: left;">
            <div class="galeria">
                <img src="../img/portal.jpg" alt="Logo del portal" />
            </div>
            <div>
                <div class="cercador left">
                    <div>
                        <label for="dataIni">Triau una data d'entrada</label>
                        <input type="text" id="dataIni" class="date right" /><br />
                    </div>
                    <div>
                        <label for="dataFi">Triau una data de sortida</label>
                        <input type="text" id="dataFi" class="date right" /><br />
                    </div>
                    <div>
                        <label>Triau el número de places</label>
                        <select id="places" class="right">
                            <option value="0">-- Triau un número --</option><%
                            for (int i = 1; i < 11; i++) { %>
                                <option><%=i%></option><%
                            } %>
                        </select>
                    </div>
                </div>
                <div class="center">
                    <button id="submitBtn" class="submitBtn">Cercar habitacions</button>
                </div>
            </div>
        </div>
        <!-- Modal que mostra l'error d'AJAX -->
        <div id="ajaxErrModal" class="reveal-modal large">
            <h2 class="error">Ups!</h2>
            <p class="lead">Sembla que hi ha hagut un error comprovant la disponibilitat</p>
            <p>Tornau a intentar-ho més tard</p>
            <a class="close-reveal-modal">&#215;</a>
	</div>
        <!-- Modal que mostra el missatge de no disponibilitat -->
        <div id="noDispModal" class="reveal-modal large">
            <h2>No hi ha disponibilitat en les dates que heu triat</h2>
            <p class="lead">Podeu provar per unes dates diferents</p>
            <a class="close-reveal-modal">&#215;</a>
	</div>
        <!-- Modal que mostra el missatge de disponibilitat trobada -->
        <div id="siDispModal" class="reveal-modal large">
            <h2>S'ha trobat disponibilitat!</h2>
            <p class="lead">En <span id="timeout">2</span> <span id="segons">segons</span> us durem a la pàgina de reserves</p>
            <a class="close-reveal-modal">&#215;</a>
	</div>
        <!-- Modal que mostra el missatge de "cercant disponibilitat" -->
        <div id="cercantModal" class="reveal-modal large">
            <h2>
                Cercant disponibilitat...<br />
                <img src="../img/loading.gif" style="height: 300px; margin-top: -100px;" />
            </h2>
	</div>
    </body>
</html>
