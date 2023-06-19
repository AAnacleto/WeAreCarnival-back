package wearecarnival.com.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Eventos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String polo;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column
    private String nomeLocal;

    @Column(nullable = false) //não precisa de columnDefinition
    private String imagem;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horarioSaida;

    @Column(nullable = false)
    private String diaSemana;

    @Column
    private boolean favoritos;

    @Column(nullable = false)
    private String categoria;
}
