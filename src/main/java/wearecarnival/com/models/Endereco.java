package wearecarnival.com.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column(unique = true, nullable = false)
    private String nomeRua;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(unique = true, nullable = false)
    private String bairro;

    @Column(unique = true, nullable = false)
    private String cidade;

    @Column(unique = true)
    private String pontoReferencia;

}
