package be.helb.misow.Mapper;

import be.helb.misow.Dto.SponsorDto;
import be.helb.misow.Model.Sponsor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-06T22:29:43+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class SponsorMapperImpl implements SponsorMapper {

    @Override
    public SponsorDto sponsorToSponsorDto(Sponsor sponsor) {
        if ( sponsor == null ) {
            return null;
        }

        SponsorDto sponsorDto = new SponsorDto();

        return sponsorDto;
    }

    @Override
    public Sponsor sponsorDtoToSponsor(SponsorDto sponsorDto) {
        if ( sponsorDto == null ) {
            return null;
        }

        String name = null;

        Sponsor sponsor = new Sponsor( name );

        return sponsor;
    }
}
