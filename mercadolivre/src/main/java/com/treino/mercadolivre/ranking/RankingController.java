package com.treino.mercadolivre.ranking;

import com.treino.mercadolivre.compra.CompraRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final Ranking ranking;
    private final CompraRepository repository;

    public RankingController(Ranking ranking, CompraRepository repository) {
        this.ranking = ranking;
        this.repository = repository;
    }


    @PostMapping
    public List<Rank> addVendas(@RequestBody @Valid RankingRequest rankingRequest){
        Rank rank = rankingRequest.toRank(repository);

        ranking.adicionaNovaCompra(rank, rankingRequest.getCompraId());
        return ranking.geraRank();
    }

    @GetMapping
    public List<Rank> getRank(){
        return ranking.geraRank();
    }
}
