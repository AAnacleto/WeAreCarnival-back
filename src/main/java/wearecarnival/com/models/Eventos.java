package wearecarnival.com.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Lob
    @Column(nullable = false) //não precisa de columnDefinition
    private byte[] imagem;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column
    private boolean favoritos;
}
