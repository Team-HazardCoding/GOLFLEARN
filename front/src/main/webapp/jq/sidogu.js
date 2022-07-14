$(function() {

	let $sidoBtn = $('div#sidebar');
	let sidoguUrl = 'http://localhost:1124/back/seeksidosigu';
	let $tagContent = $('#tag-container');
	$sidoBtn.on('click', 'li', function(event) {
		$tagContent.empty();
		let $sidoVal = $(this);
		// console.log($sidoVal.val());
		$.ajax({
			url: sidoguUrl,
			data: {sido : $sidoVal.val()},
			success: function(jsonObj) {
				let sigu='';
				$(jsonObj.sigungu).each(function(index, item) {
					// console.log(item);
					$keyObj = Object.keys(item);
					$itemObj = Object.values(item);
					for ( let i = 0; i < $itemObj.length; i++ ) {
						// arr.push($itemObj[i]);
						// console.log($keyObj);
						sigu += '<li class="sigu" name="sigu" value="' + $keyObj[i] + '"';
						sigu+= '>' + $itemObj[i] + '</li>';	
					}
				})
				// sigu += '</ul>';
				
				$tagContent.append(sigu);
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

	$('#tag-container').on('click','li',function() {
		
		$.ajax({
			url: "localhost:1124/back/filter",
			success: function() {
				
			},
			error: function(jqXHR) {
				alert("error: " + jqXHR.status);
			}
		})
		
		
	})

})
