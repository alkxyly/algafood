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