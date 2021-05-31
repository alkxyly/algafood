function consultarRestaurantes() {
  $.ajax({
    url: "http://localhost:8080/restaurantes",
    type: "get",

    success: function(response) {
      $("#conteudo").text(JSON.stringify(response));
    }
  });
}

function fecharRestaurante() {
  $.ajax({
    url: "http://api.algafood.local:8080/restaurantes/1/fechamento",
    type: "put",

    success: function(response) {
      alert("Restaurante foi fechado!");
    }
  });
}


function consultar() {
  $.ajax({
    url: "http://localhost:8080/formas-pagamento",
    type: "get",

    success: function(response) {
	  console.log(JSON.stringify(response));
      preencherTabela(response);
    }
  });
}


function cadastrar(){
	var formaPagamentoJson = JSON.stringify( {
		"descricao": $("#campo-descricao").val()
	});
	
	console.log(formaPagamentoJson);
	
	$.ajax({
	    url: "http://localhost:8080/formas-pagamento",
	    type: "post",
		data: formaPagamentoJson,
		contentType: "application/json",
		
	    success: function(response) {
		   alert("Forma de Pagamento adicionada!");
		   consultar();
	    },

		error: function(error){
			if(error.status == 400){
				var problem = JSON.parse(error.responseText);
				alert(problem.userMessage);
			}else
			    alert("Erro ao cadastrar forma de pagamento");
		}
  	});
}

function preencherTabela(formasPagamento) {
  $("#tabela tbody tr").remove();

  $.each(formasPagamento, function(i, formaPagamento) {
    var linha = $("<tr>");

    linha.append(
      $("<td>").text(formaPagamento.id),
      $("<td>").text(formaPagamento.descricao)
    );

    linha.appendTo("#tabela");
  });
}

$("#btn-consultar").click(consultar);
$("#btn-cadastrar").click(cadastrar);