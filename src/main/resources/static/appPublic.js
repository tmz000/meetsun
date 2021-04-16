//好评星级1-5（1非常差2差3一般4满意5非常满意）
function getStarLv(i){
	if(i == "1"){
		return "非常差";
	}else if(i == "2"){
		return "差";
	}else if(i == "3"){
		return "一般";
	}else if(i == "4"){
		return "满意";
	}else if(i == "5"){
		return "非常满意";
	}else{
		return ""
	}
}