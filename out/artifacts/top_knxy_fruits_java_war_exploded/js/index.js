let Reminder = {
    init: function () {
        let host;
        if (window.location.protocol == 'http:') {
            host = 'ws://' + window.location.host + '/admin/remind';
        } else {
            host = 'wss://' + window.location.host + '/admin/remind';
        }

        let socket;
        if ('WebSocket' in window) {
            socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            socket = new MozWebSocket(host);
        } else {
            Console.log('Error: WebSocket is not supported by this browser.');
            return;
        }
        socket.onopen = function () {
            console.log("WebSocket", "open");
        };
        socket.onclose = function () {
            console.log("WebSocket", "close");
        };
        socket.onmessage = function (message) {
            console.log("WebSocket", "message : " + message);
            Manager.remind(message)
        };
    },

}

let Manager = {
    remind: function (msg) {
        let m = document.getElementById('sound_remind');
        m.play();//播放
        Web.request("M1017", {}, {
            onSuccess: function (rp) {
                let value = rp.data.value;
                $("#red_point").html(value);
                value = Number(value);
                if (value < 1) {
                    $("#red_point").hide();
                } else {
                    $("#red_point").show();
                }
            },
            onError: function (rp) {

            }
        });
    }
}
