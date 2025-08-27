const main = document.getElementById("main");
const history = document.getElementById("history");
const historyList = [];
const statusIndicator = document.getElementById("connection-status");
const statusText = document.getElementById("status-text");

const socket = new SockJS('/ws');
const stomp = Stomp.over(socket);

stomp.connect({}, function() {
    statusIndicator.classList.add('connected');
    statusText.textContent = 'Conectado';
    
    stomp.subscribe("/topic/calls", function(message) {
        const call = JSON.parse(message.body);
        const callText = `Cliente: ${call.name} <br>Balcão:  ${call.counter}`;
        
        // Adiciona ao histórico
        historyList.unshift(callText);
        if (historyList.length > 3) historyList.pop();
        
        // Atualiza a exibição
        main.innerHTML = callText;
        main.classList.add('active');
        
        // Atualiza o histórico
        history.innerHTML = historyList.map(c => `<div id="history-content">${c}</div>`).join('');
        
        // Remove a classe de destaque após a animação
        setTimeout(() => {
            main.classList.remove('active');
        }, 2000);
    });
}, function(error) {
    statusText.textContent = 'Erro na conexão' + err;
    console.error('Erro na conexão:', error);
});