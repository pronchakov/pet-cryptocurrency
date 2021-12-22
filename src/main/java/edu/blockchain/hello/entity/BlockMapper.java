package edu.blockchain.hello.entity;

import edu.blockchain.hello.db.BlockDB;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockMapper {

    BlockDB map(Block block);
    Block map(BlockDB blockDB);

}
