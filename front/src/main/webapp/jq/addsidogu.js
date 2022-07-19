$(function() {

	let $sidoBtn = $('div#sidebar');
	let sidoguUrl = 'http://localhost:1124/back/seeksidosigu';
	let $sidoCombo = $('select[name=sd]'); // 첫번째 시도를 선택하여 가져와야 함 option에 selected 된것을 가져와야함
	let $sigunguCombo = $('select[name=sgg]');
	$sidoCombo.change(function() {
		// $sidoCombo.empty(); //사용자가 콤보박스를여러번 클릭했을때 append 되는것을 방지함
        $sigunguCombo.empty();
		let $sidoVal = $(this);
		console.log($sidoVal.val());
        let sigungu='<option value="none">=== 선택 ===</option>';
        
		$.ajax({
			url: sidoguUrl,
			data: {sido : $sidoVal.val()},
			success: function(jsonObj) {
                
				$(jsonObj.sigungu).each(function(index, item) {
					// console.log(item);
					$keyObj = Object.keys(item);
					$itemObj = Object.values(item);
					for ( let i = 0; i < $itemObj.length; i++ ) {
						// arr.push($itemObj[i]);
						console.log($keyObj);
						sigungu += '<option class="sigungu" name="sigungu" value="' + $keyObj[i] + '"';
						sigungu+= '>' + $itemObj[i] + '</option>';	
					}
				})
				// sigu += '</ul>';
				
				$sigunguCombo.append(sigungu);
                $sigunguCombo.change(function() {
                    let $sigunguVal = $(this);
                    console.log($sigunguVal.val());
                })
				// console.log($sidoNm);
				// 각 시군구가 tag-name에 뿌려져야 함.
				// tag-name 을 리스트로 받아와서 반복문을 돌아서 뿌려줌
				// event.preventDefault();
				return false;
			},
			error: function(jqXHR){
				alert("error: " + jqXHR.status);
				
			}
			
		})
	});
})
