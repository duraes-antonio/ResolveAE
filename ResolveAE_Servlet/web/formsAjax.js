function onloadAvaliacao() {
    var dataForm;
    dataForm = document.getElementById("formsAvaliacao");
    var requestMethod = dataForm.method;
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(requestMethod, 'Avaliacao', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadComentario() {
    var dataForm;
    dataForm = document.getElementById("formsComentario");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Comentario', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadContato() {
    var dataForm;
    dataForm = document.getElementById("formsContato");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Contato', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadContrato() {
    var dataForm;
    dataForm = document.getElementById("formsContrato");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Contrato', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadEndereco() {
    var dataForm;
    dataForm = document.getElementById("formsEndereco");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Endereco', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadHorario() {
    var dataForm;
    dataForm = document.getElementById("formsHorario");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Horario', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadInfoProfissional() {
    var dataForm;
    dataForm = document.getElementById("formsInfoProfissional");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'InfoProfissional', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadServico() {
    var dataForm;
    dataForm = document.getElementById("formsServico");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Servico', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}

function onloadUsuario() {
    var dataForm;
    dataForm = document.getElementById("formsUsuario");
    dataForm.onsubmit = function (e) {
        e.preventDefault();
        var data = {};
        for (var i = 0; i < dataForm.length; i++) {
            var input = dataForm[i];
            console.log(input);
            if (input.name) {
                if (input.name !== 'methodx') {
                    data[input.name] = input.value;
                }
                else {
                    data['method'] = input.value;
                }
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open(dataForm.method, 'Usuario', true);
        xhr.setRequestHeader('Content-Type', 'aplplication/json; charset=UTF-8');
        xhr.send(JSON.stringify(data));
        xhr.onloadend = function () {
            alert("Requisicao enviada");
        };
    };
}