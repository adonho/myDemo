/**
 * Created by Administrator on 15-12-24.
 */

(function () {

    function Entry(x, y) {
        this.x = x;
        this.y = y;
    }

    var _x = 4, _y = 4, EMPTY = '　', emptyList = new Array(_x * _y), emptySize = 0, isChange, handling = false;
    var $container, sum, source, gameOver;
    var data, indexer, map;

    function init() {
        $container = document.createElement('div');
        document.body.appendChild($container);
        sum = 0;
        source = 0;
        gameOver = false;
        data = [];
        indexer = [];
        map = [];

        for (var x = 0; x < _x; x++) {
            data.push([]);
            map.push([]);
            indexer.push([]);
            for (var y = 0; y < _y; y++) {
                data[x].push(EMPTY);
                map[x].push(EMPTY);
                indexer[x].push(new Entry(x, y));
            }
        }
        var winWidth = Math.min(window.innerHeight, window.innerWidth);

        var padiing = winWidth / 12, width = winWidth - padiing;
        padiing = padiing / _y;
        width = width / _y;
        var top = padiing / 2, distance = width + padiing;
        for (var x = 0; x < map.length; x++) {
            for (var y = 0; y < map[x].length; y++) {
                var node = document.createElement('span');
                $container.appendChild(node);
                map[x][y] = node;
                var style = 'position:absolute;top:'+(top + distance * y)+'px;left:'+(top + distance * x)+'px;width:'+width+'px;height:'+width+'px;';
                map[x][y].setAttribute('style',style);
            }
        }
    }

    // 开始游戏
    function startGame() {

        for (var x = 0; x < data.length; x++) {
            for (var y = 0; y < data[x].length; y++) {
                data[x][y] = EMPTY;
            }
        }
        data[0][0] = randomValue();
        data[1][0] = randomValue();
        sum = data[0][0] + data[1][0];
        source = 0;
        gameOver = false;
        loadData();
    }

// 加载数据，渲染样式
    function loadData() {
        for (var x = 0; x < map.length; x++) {
            for (var y = 0; y < map[x].length; y++) {
                if(EMPTY === data[x][y]){
                    map[x][y].setAttribute('class', 'c');
                } else {
                    map[x][y].setAttribute('class', 'c' + data[x][y]);
                }
                map[x][y].innerHTML = data[x][y];
            }
        }
    }

// 上
    function up() {

        for (var x = 0; x < _x; x++) {
            var e = 0, v = 0, hasE = false, hasV = false;

            for (var y = 0; y < _y; y++) {
                if (data[x][y] === EMPTY) {
                    if (y === 0) {
                        e = y;
                        hasE = true;
                    } else {
                        if (!hasE) {
                            e = y;
                            hasE = true;
                        }
                    }
                } else {
                    if (y === 0) {
                        v = y;
                        hasV = true;
                    } else {
                        // 非空值
                        if (hasV) {
                            if (data[x][v] == data[x][y]) {
                                data[x][v] <<= 1;
                                data[x][y] = EMPTY;
                                hasV = false;
                                isChange = true;
                                if (!hasE) {
                                    e = y;
                                    hasE = true;
                                }
                            } else {
                                if (hasE) {
                                    data[x][e] = data[x][y];
                                    data[x][y] = EMPTY;
                                    isChange = true;
                                    v = e;
                                    e++;
                                } else {
                                    v = y;
                                }
                            }
                        } else {
                            if (hasE) {
                                data[x][e] = data[x][y];
                                data[x][y] = EMPTY;
                                isChange = true;
                                v = e;
                                e++;
                            } else {
                                v = y;
                            }
                            hasV = true;
                        }
                    }
                }
            }
            for (var y = 0; y < _y; y++) {
                if (data[x][y] === EMPTY)
                    emptyValue(x, y);
            }
        }


    }

// 下
    function down() {

        for (var x = 0; x < _y; x++) {
            var e = _y - 1, v = _y - 1, hasE = false, hasV = false;

            for (var y = _y - 1; y >= 0; y--) {
                if (data[x][y] === EMPTY) {
                    if (y === (_y - 1)) {
                        e = y;
                        hasE = true;
                    } else {
                        if (!hasE) {
                            e = y;
                            hasE = true;
                        }
                    }
                } else {
                    if (y === (_y - 1)) {
                        v = y;
                        hasV = true;
                    } else {
                        // 非空值
                        if (hasV) {
                            if (data[x][v] == data[x][y]) {
                                data[x][v] <<= 1;
                                data[x][y] = EMPTY;
                                hasV = false;
                                isChange = true;
                                if (!hasE) {
                                    e = y;
                                    hasE = true;
                                }
                            } else {
                                if (hasE) {
                                    data[x][e] = data[x][y];
                                    data[x][y] = EMPTY;
                                    isChange = true;
                                    v = e;
                                    e--;
                                } else {
                                    v = y;
                                }
                            }
                        } else {
                            if (hasE) {
                                data[x][e] = data[x][y];
                                data[x][y] = EMPTY;
                                isChange = true;
                                v = e;
                                e--;
                            } else {
                                v = y;
                            }
                            hasV = true;
                        }
                    }
                }
            }
            for (var y = 0; y < _y; y++) {
                if (data[x][y] === EMPTY)
                    emptyValue(x, y);
            }
        }


    }

// 左
    function left() {

        for (var y = 0; y < _y; y++) {
            var e = 0, v = 0, hasE = false, hasV = false;

            for (var x = 0; x < _x; x++) {
                if (data[x][y] === EMPTY) {
                    if (x == 0) {
                        e = x;
                        hasE = true;
                    } else {
                        if (!hasE) {
                            e = x;
                            hasE = true;
                        }
                    }
                } else {
                    if (x == 0) {
                        v = x;
                        hasV = true;
                    } else {
                        // 非空值
                        if (hasV) {
                            if (data[v][y] == data[x][y]) {
                                data[v][y] <<= 1;
                                data[x][y] = EMPTY;
                                hasV = false;
                                isChange = true;
                                if (!hasE) {
                                    e = x;
                                    hasE = true;
                                }
                            } else {
                                if (hasE) {
                                    data[e][y] = data[x][y];
                                    data[x][y] = EMPTY;
                                    isChange = true;
                                    v = e;
                                    e++;
                                } else {
                                    v = x;
                                }
                            }
                        } else {
                            if (hasE) {
                                data[e][y] = data[x][y];
                                data[x][y] = EMPTY;
                                isChange = true;
                                v = e;
                                e++;
                            } else {
                                v = x;
                            }
                            hasV = true;
                        }
                    }
                }
            }
            for (var x = 0; x < _x; x++) {
                if (data[x][y] === EMPTY)
                    emptyValue(x, y);
            }
        }


    }

// 右
    function right() {

        for (var y = 0; y < _y; y++) {
            var e = _x - 1, v = _x - 1, hasE = false, hasV = false;

            for (var x = _x - 1; x >= 0; x--) {
                if (data[x][y] === EMPTY) {
                    if (x === (_x - 1)) {
                        e = x;
                        hasE = true;
                    } else {
                        if (!hasE) {
                            e = x;
                            hasE = true;
                        }
                    }
                } else {
                    if (x === (_x - 1)) {
                        v = x;
                        hasV = true;
                    } else {
                        // 非空值
                        if (hasV) {
                            if (data[v][y] == data[x][y]) {
                                data[v][y] <<= 1;
                                data[x][y] = EMPTY;
                                hasV = false;
                                isChange = true;
                                if (!hasE) {
                                    e = x;
                                    hasE = true;
                                }
                            } else {
                                if (hasE) {
                                    data[e][y] = data[x][y];
                                    data[x][y] = EMPTY;
                                    isChange = true;
                                    v = e;
                                    e--;
                                } else {
                                    v = x;
                                }
                            }
                        } else {
                            if (hasE) {
                                data[e][y] = data[x][y];
                                data[x][y] = EMPTY;
                                isChange = true;
                                v = e;
                                e--;
                            } else {
                                v = x;
                            }
                            hasV = true;
                        }
                    }
                }
            }
            for (var x = 0; x < _x; x++) {
                if (data[x][y] === EMPTY)
                    emptyValue(x, y);
            }
        }


    }

// 事件前处理
    function beforeEvent() {
        emptySize = 0;
        isChange = false;
    }

    function afterEvent() {

        if (!isChange) return;

        var e = emptyList[parseInt(Math.random() * emptySize)];
        var val = randomValue();
        data[e.x][e.y] = val;
        source = sum;
        sum += val;
        loadData();
        if (emptySize == 1) {
            if ((gameOver = checkCanMove())) {
                alert('游戏结束');
            }
        }
    }

    function checkCanMove() {
        for (var x = 0; x < _x; x++) {
            for (var y = 0; y < _y - 1; y++) {
                if (data[x][y] === data[x][y + 1])
                    return false;
            }
        }
        for (var y = 0; y < _y; y++) {
            for (var x = 0; x < _x - 1; x++) {
                if (data[x][y] === data[x + 1][y])
                    return false;
            }
        }
        return true;
    }

    function emptyValue(x, y) {
        emptyList[emptySize++] = indexer[x][y];
    }


// 随机 2\4
    function randomValue() {
        return 2 << (parseInt(Math.random() * 8 + 1) >> 3);
    }

    window.onload = function(){
        init();
        startGame();
        window.onkeydown = function(e){
            if (!gameOver && !handling) {
                handling = true;
                try {
                    switch (e.keyCode) {
                        case 37:
                            beforeEvent();
                            left();
                            afterEvent();
                            break;
                        case 38:
                            beforeEvent();
                            up();
                            afterEvent();
                            break;
                        case 39:
                            beforeEvent();
                            right();
                            afterEvent();
                            break;
                        case 40:
                            beforeEvent();
                            down();
                            afterEvent();
                            break;
                        default :
                    }
                } finally {
                    handling = false;
                }
            }
        }
    }
})();
