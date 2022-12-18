package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_CLI)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CLI_GET_ALL,
            query = JpaConst.Q_CLI_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_CLI_COUNT,
            query = JpaConst.Q_CLI_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_CLI_GET_ALL_MINE,
            query = JpaConst.Q_CLI_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_CLI_COUNT_ALL_MINE,
            query = JpaConst.Q_CLI_COUNT_ALL_MINE_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @Column(name = JpaConst.CLI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.CLI_COL_EMP, nullable = false)
    private Employee employee;

    @Column(name = JpaConst.CLI_COL_NAME, nullable = false)
    private String name;

    @Column(name = JpaConst.CLI_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;


}