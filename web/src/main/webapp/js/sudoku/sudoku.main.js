/**
 * Created by adon on 2016/2/24 0024.
 */

var data = [['','','','','','',5,'','']
	,['','','',3,7,'',2,1,9]
	,[3,'',9,'',8,5,'',7,'']
	,[2,'','','','',3,'',9,'']
	,[5,7,'',8,9,2,'',4,3]
	,['',9,'',4,'',7,'','',5]
	,['',8,'',5,3,'',9,'',1]
	,[6,3,1,'',2,8,'','','']
	,['','',5,'','','','','','']];


function Sudoku(data){

	var _sd = this;

	var BIT_ARR = [0, 1, 2, 4, 8, 16, 32, 64, 128, 256];

	this.data = createEmptySpace();
	for(var i=0; i<data.length;i++){
		for(var j=0; j<data[i].length; j++){
			this.data[i][j] = new Entity(i, j, data[i][j]);
		}
	}

	function Entity(x, y, value){
		var _e = this;
		var hv = !(typeof value === 'undefined' || value=='' || value==0)

		this.showVal = hv? value : '';
		this.val = hv? BIT_ARR[value] : 0;

		this.x = x;
		this.y = y;

		this.sX = parseInt(this.x/3)*3;
		this.sY = parseInt(this.y/3)*3;

		this.enableVal = function(){
			if(_e.val>0) return ;
			var v = 0;
			for (var i=0; i<9; i++){
				v = v|_sd.data[_e.x][i].val|_sd.data[i][_e.y].val;
			}
			for (var i = _e.sX; i<(_e.sX+3); i++){
				for (var j = _e.sY; j<(_e.sY+3); j++){
					v = v|_sd.data[i][j].val;
				}
			}
			return ~v;
		}

		this.enable = function(){
			if(_e.val>0) return ;
			var ev = _e.enableVal();
			var arr = [];
			for(var i = 1; i<BIT_ARR.length; i++){
				if(BIT_ARR[i]&ev){
					arr.push(i);
				}
			}
			return arr;
		}

	}

	function createEmptySpace(){
		var a = [];
		for(var i=0; i<9; i++){
			a.push(new Array(9));
		}
		return a;
	}

	this.valid = {}

}


