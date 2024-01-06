package be.helb.misow.Mapper;

import be.helb.misow.Dto.SponsorDto;
import be.helb.misow.Model.Sponsor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// Interface MapStruct pour mapper entre les objets Sponsor et SponsorDto
@Mapper(componentModel = "spring")
public interface SponsorMapper {

    // Méthode pour convertir un objet Sponsor en SponsorDto
    SponsorDto sponsorToSponsorDto(Sponsor sponsor);

    // Méthode pour convertir un objet SponsorDto en Sponsor
    Sponsor sponsorDtoToSponsor(SponsorDto sponsorDto);

   
}
