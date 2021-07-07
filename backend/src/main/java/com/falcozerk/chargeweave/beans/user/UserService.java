package com.falcozerk.chargeweave.beans.user;

import com.falcozerk.chargeweave.auth.UserPrincipal;
import com.falcozerk.chargeweave.beans.charger.Charger;
import com.falcozerk.chargeweave.beans.common.CwBean;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.app.Importer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService extends CwService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
            () -> new RuntimeException("User not found: " + id)
        );

        return UserPrincipal.create(user);
    }

    public void importFrom(Importer pImporter, Workbook pWorkbook, int pTabId) {
        pImporter.init( pWorkbook, pTabId );
        ArrayList<Cell> cellList = new ArrayList<>();

        while( pImporter.importRow( cellList ) ) {
            userRepo.save( createFrom( pImporter, cellList ) );
        }
    }

    public User createFrom(Importer pImporter, ArrayList<Cell> pCellList ) {
        User user = new User();

        user.setHandle( pImporter.getNextString( pCellList ) );
        user.setRegion( pImporter.getNextString( pCellList ) );
        user.setLeader( pImporter.getNextString( pCellList ) );
        user.setBadges( pImporter.getNextString( pCellList ) );
        user.setCentury( pImporter.getNextString( pCellList ) );
        user.setCompetitorId( pImporter.getNextLong( pCellList ) );

        user.setUsername( user.getHandle() );
        user.setPassword( user.getHandle() );

        return user;
    }
}